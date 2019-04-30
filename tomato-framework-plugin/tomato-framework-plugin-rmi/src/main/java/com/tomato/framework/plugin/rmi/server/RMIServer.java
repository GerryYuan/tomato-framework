package com.tomato.framework.plugin.rmi.server;

public interface RMIServer {
    
    void publish(final Object service, int port);
    
    
}
