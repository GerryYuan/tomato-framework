package com.tomato.framework.plugin.rmi.discovery;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Discovery {
    
    <T extends Remote> T getBean(String className) throws RemoteException, NotBoundException, MalformedURLException;
}
