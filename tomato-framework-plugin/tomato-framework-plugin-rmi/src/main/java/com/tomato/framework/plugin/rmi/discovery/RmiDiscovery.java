package com.tomato.framework.plugin.rmi.discovery;

import com.tomato.framework.plugin.rmi.exception.RmiException;
import java.rmi.Naming;
import java.rmi.Remote;

public class RmiDiscovery implements Discovery {
    
    private String address = "rmi://127.0.0.1:18080";
    
    public RmiDiscovery() {
    }
    
    public RmiDiscovery(String address) {
        this.address = address;
    }
    
    @Override
    public <T extends Remote> T getBean(Class<T> clazz) {
        try {
            return (T) Naming.lookup(address.concat("/").concat(clazz.getName()));
        } catch (Exception e) {
            throw new RmiException(e);
        }
    }
}
