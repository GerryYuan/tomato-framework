package com.tomato.framework.plugin.rmi.server;

import com.google.common.collect.Lists;
import com.tomato.framework.plugin.rmi.exception.RmiException;
import com.tomato.framework.plugin.rmi.register.Register;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;

public class Rmi2Server implements Server {
    
    private String address = "rmi://127.0.0.1";
    
    private int port = 18080;
    
    private List<Register> registers = Lists.newArrayListWithCapacity(100);
    
    private Registry registry;
    
    public Rmi2Server(Register... register) {
        Arrays.stream(register).forEach(r -> this.registers.add(r));
    }
    
    public Rmi2Server(String address, int port, Register register) {
        this.address = address;
        this.port = port;
        this.registers.add(register);
    }
    
    @Override
    public void start() throws RemoteException {
        this.registry = LocateRegistry.createRegistry(port);
        registers.stream().forEach(r -> {
            try {
                r.register(address, port);
            } catch (Exception e) {
                throw new RmiException(e);
            }
        });
    }
    
    @Override
    public void stop() {
        System.exit(0);
    }
    
}
