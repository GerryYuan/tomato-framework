package com.tomato.framework.plugin.rmi.register;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RmiRegister implements Register {
    
    private String className;
    
    private Object service;
    
    public RmiRegister(String className, Object service) {
        this.className = className;
        this.service = service;
    }
    
    @Override
    public <T extends Remote> void register(String address, int port) throws RemoteException, MalformedURLException {
        Naming.rebind(address.concat(":" + port) + "/" + className, (Remote) service);
    }
}
