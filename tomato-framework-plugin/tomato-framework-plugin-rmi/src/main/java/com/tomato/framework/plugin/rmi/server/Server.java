package com.tomato.framework.plugin.rmi.server;

import java.rmi.RemoteException;

public interface Server {
    
    void start() throws RemoteException;
    
    void stop();
    
}
