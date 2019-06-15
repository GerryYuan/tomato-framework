package com.tomato.framework.plugin.rmi.server;

import com.tomato.framework.core.util.IPUtils;
import com.tomato.framework.plugin.rmi.register.Register;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiServer implements Server {
    
    private int port = 18081;
    
    private Register register;
    
    public RmiServer(int port, Register register) {
        this.port = port;
        this.register = register;
    }
    
    @Override
    public void start() throws RemoteException {
        String address = "rmi://".concat(IPUtils.getLocalIp()).concat(":").concat(String.valueOf(port));
        LocateRegistry.createRegistry(port);
        register.register(address);
    }
    
    @Override
    public void stop() {
        System.exit(0);
    }
    
}
