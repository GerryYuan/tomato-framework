package com.tomato.framework.plugin.rmi.discovery;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tomato.framework.core.util.EmptyUtils;
import com.tomato.framework.plugin.rmi.exception.RmiException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

@Slf4j
public class ZKDiscovery implements Discovery {
    
    private String address;
    
    private CuratorFramework curatorFramework;
    
    private final static String ZK_BASE_NODE = "rmi";
    
    private final static int ZK_CONNECT_TIME_OUTMS = 5000;
    
    private final static int ZK_SESSION_TIME_OUTMS = 5000;
    
    private final static int ZK_RETRY_SLEEP_TIMEMS = 1000;
    
    private final static int ZK_RETRIES = 3;
    
    private Map<String, List<String>> services = Maps.newConcurrentMap();
    
    public ZKDiscovery(String address) {
        this.address = address;
        this.curatorFramework = CuratorFrameworkFactory.builder().connectString(address)
            .sessionTimeoutMs(ZK_SESSION_TIME_OUTMS)
            .connectionTimeoutMs(ZK_CONNECT_TIME_OUTMS)
            .retryPolicy(new ExponentialBackoffRetry(ZK_RETRY_SLEEP_TIMEMS, ZK_RETRIES))
            .namespace(ZK_BASE_NODE).build();
        curatorFramework.start();
        init();
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/", true);
        pathChildrenCache.getListenable().addListener((client, event) -> {
            switch (event.getType()) {
                case CHILD_REMOVED:
                    services.remove(event.getData().getPath());
                    System.out.println("服务下线：" + event.getData().getPath());
                default:
                    break;
            }
        });
        try {
            pathChildrenCache.start();
        } catch (Exception e) {
            throw new RmiException(e);
        }
    }
    
    private void init() {
        try {
            String path = "/";
            List<String> services = curatorFramework.getChildren().forPath(path);
            if (EmptyUtils.isNotEmpty(services)) {
                services.forEach(s -> {
                    try {
                        String address = new String(curatorFramework.getData().forPath(path.concat(s)));
                        this.services.put(s, Lists.newArrayList(address));
                    } catch (Exception e) {
                        throw new RmiException(e);
                    }
                });
            }
        } catch (Exception e) {
            log.error("无服务提供者", e);
        }
        
    }
    
    @Override
    public <T extends Remote> T getBean(Class<T> clazz) {
        try {
            String className = clazz.getName();
            if (EmptyUtils.isEmpty(this.services) || !services.keySet().contains(className)) {
                throw new RmiException("没有相关服务提供者【" + className + "】");
            }
            List<String> paths = services.get(className);
            return (T) Naming.lookup(paths.get(0).concat("/").concat(className));
        } catch (Exception e) {
            throw new RmiException(e);
        }
    }
}
