package com.tomato.framework.plugin.rmi.server;

import com.tomato.framework.plugin.rmi.register.Register;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiServer implements Server {
    
    private String address = "rmi://127.0.0.1";
    
    private int port = 18081;
    
    private Register register;
    
    public RmiServer(Register register) {
        this.register = register;
    }
    
    @Override
    public void start() throws RemoteException {
        LocateRegistry.createRegistry(port);
        register.register(address, port);
    }
    
    @Override
    public void stop() {
        System.exit(0);
    }
    
}
