package com.tomato.framework.plugin.rmi.discovery;

import com.tomato.framework.plugin.rmi.exception.RmiException;
import java.rmi.Naming;
import java.rmi.Remote;

public class RmiDiscovery implements Discovery {
    
    private String address = "rmi://127.0.0.1";
    
    private int port = 18080;
    
    public RmiDiscovery() {
    }
    
    public RmiDiscovery(String address, int port) {
        this.address = address;
        this.port = port;
    }
    
    @Override
    public <T extends Remote> T getBean(Class<T> clazz) {
        try {
            return (T) Naming.lookup(address.concat(":" + port) + "/" + clazz.getSimpleName());
        } catch (Exception e) {
            throw new RmiException(e);
        }
    }
}
