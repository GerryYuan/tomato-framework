package com.tomato.framework.plugin.rmi.register;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Register {
    
    <T extends Remote> void register(String address, int port) throws RemoteException, MalformedURLException;
}
