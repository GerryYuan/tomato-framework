package com.tomato.framework.plugin.rmi.server;

import com.tomato.framework.plugin.rmi.register.Register;
import java.rmi.RemoteException;

public class RmiServer implements Server {
    
    private String address = "rmi://127.0.0.1:18081";
    
    private Register register;
    
    public RmiServer(String address, Register register) {
        this.address = address;
        this.register = register;
    }
    
    @Override
    public void start() throws RemoteException {
        register.register(address);
    }
    
    @Override
    public void stop() {
        System.exit(0);
    }
    
}
