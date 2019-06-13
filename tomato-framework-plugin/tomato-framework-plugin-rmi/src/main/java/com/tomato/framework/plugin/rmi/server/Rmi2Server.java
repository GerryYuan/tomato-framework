package com.tomato.framework.plugin.rmi.server;

import com.tomato.framework.plugin.rmi.register.Register;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Rmi2Server implements Server {
    
    private String address = "rmi://127.0.0.1";
    
    private int port = 18080;
    
    private Register register;
    
    public Rmi2Server(Register register) {
        this.register = register;
    }
    
    public Rmi2Server(String address, int port, Register register) {
        this.address = address;
        this.port = port;
        this.register = register;
    }
    
    @Override
    public void start() throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(port);
        register.register(address, port);
    }
    
    @Override
    public void stop() {
    }
    
}
