package com.tomato.framework.plugin.rmi.register;

import com.tomato.framework.plugin.rmi.exception.RmiException;
import java.rmi.Naming;
import java.rmi.Remote;

public class RmiRegister implements Register {
    
    private String className;
    
    private Object service;
    
    public RmiRegister(String className, Object service) {
        this.className = className;
        this.service = service;
    }
    
    @Override
    public <T extends Remote> void register(String address) {
        try {
            Naming.rebind(address.concat("/").concat(className), (Remote) service);
        } catch (Exception e) {
            throw new RmiException(e);
        }
    }
}