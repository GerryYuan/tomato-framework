package com.tomato.framework.plugin.rmi.register;

import com.google.common.collect.Maps;
import com.tomato.framework.plugin.rmi.exception.RmiException;
import java.nio.charset.Charset;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.Map;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class ZKRegister implements Register {
    
    private Map<String, Object> services = Maps.newConcurrentMap();
    
    private String address;
    
    private CuratorFramework curatorFramework;
    
    private final static String ZK_BASE_NODE = "rmi";
    
    private final static int ZK_CONNECT_TIME_OUTMS = 5000;
    
    private final static int ZK_SESSION_TIME_OUTMS = 5000;
    
    private final static int ZK_RETRY_SLEEP_TIMEMS = 1000;
    
    private final static int ZK_RETRIES = 3;
    
    public <T extends Remote> ZKRegister(String address, Map<String, T> services) {
        this.services.putAll(services);
        this.address = address;
        this.curatorFramework = CuratorFrameworkFactory.builder().connectString(address)
            .sessionTimeoutMs(ZK_SESSION_TIME_OUTMS)
            .connectionTimeoutMs(ZK_CONNECT_TIME_OUTMS)
            .retryPolicy(new ExponentialBackoffRetry(ZK_RETRY_SLEEP_TIMEMS, ZK_RETRIES))
            .namespace(ZK_BASE_NODE).build();
        curatorFramework.start();
    }
    
    @Override
    public <T extends Remote> void register(String address) {
        services.forEach((k, v) -> {
            try {
                curatorFramework.create().withMode(CreateMode.EPHEMERAL)
                    .forPath("/".concat(k), address.getBytes(Charset.defaultCharset()));
                Naming.rebind(address.concat("/").concat(k), (Remote) v);
            } catch (Exception e) {
                throw new RmiException(e);
            }
        });
        
    }
}
