package com.tomato.framework.plugin.rmi.server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public interface Server {
    
    void start() throws RemoteException, MalformedURLException;
    
    void stop();
    
}
