package com.tomato.framework.plugin.rmi.discovery;

import com.google.common.collect.Lists;
import com.tomato.framework.core.util.EmptyUtils;
import com.tomato.framework.plugin.rmi.exception.RmiException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

@Slf4j
public class ZKDiscovery implements Discovery {
    
    private String address;
    
    private int port;
    
    private CuratorFramework curatorFramework;
    
    private final static String ZK_BASE_NODE = "rmi";
    
    private final static int ZK_CONNECT_TIME_OUTMS = 5000;
    
    private final static int ZK_SESSION_TIME_OUTMS = 5000;
    
    private final static int ZK_RETRY_SLEEP_TIMEMS = 1000;
    
    private final static int ZK_RETRIES = 3;
    
    private List<String> services = Lists.newArrayList();
    
    public ZKDiscovery(String address, int port) {
        this.address = address;
        this.port = port;
        this.curatorFramework = CuratorFrameworkFactory.builder().connectString(address.concat(":" + port))
            .sessionTimeoutMs(ZK_SESSION_TIME_OUTMS)
            .connectionTimeoutMs(ZK_CONNECT_TIME_OUTMS)
            .retryPolicy(new ExponentialBackoffRetry(ZK_RETRY_SLEEP_TIMEMS, ZK_RETRIES))
            .namespace(ZK_BASE_NODE).build();
        curatorFramework.start();
        init();
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, ZK_BASE_NODE, true);
        pathChildrenCache.getListenable().addListener((client, event) -> {
            switch (event.getType()) {
                case CHILD_REMOVED:
                    services.remove(event.getData().getPath());
                default:
                    break;
            }
        });
    }
    
    private void init() {
        try {
            List<String> services = curatorFramework.getChildren().forPath("/");
            if (EmptyUtils.isNotEmpty(services)) {
                this.services.addAll(services);
            }
        } catch (Exception e) {
            log.error("无服务提供者", e);
        }
        
    }
    
    @Override
    public <T extends Remote> T getBean(Class<T> clazz) {
        try {
            String className = "/".concat(clazz.getSimpleName());
            if (EmptyUtils.isEmpty(this.services) || !services.contains(className)) {
                throw new RmiException("没有相关服务提供者【" + className + "】");
            }
            return (T) Naming.lookup("rmi://".concat(address).concat(":" + port) + "/" + className);
        } catch (Exception e) {
            throw new RmiException(e);
        }
    }
}
