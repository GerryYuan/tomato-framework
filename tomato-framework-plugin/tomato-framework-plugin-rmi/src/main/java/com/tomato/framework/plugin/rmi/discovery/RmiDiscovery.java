package com.tomato.framework.plugin.rmi.discovery;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RmiDiscovery implements Discovery {
    
    private String address = "rmi://127.0.0.1";
    
    private int port;
    
    public RmiDiscovery(String address, int port) {
        this.address = address;
        this.port = port;
    }
    
    @Override
    public <T extends Remote> T getBean(String className)
        throws RemoteException, NotBoundException, MalformedURLException {
        return (T) Naming.lookup(address.concat(":" + port) + "/" + className);
    }
}
