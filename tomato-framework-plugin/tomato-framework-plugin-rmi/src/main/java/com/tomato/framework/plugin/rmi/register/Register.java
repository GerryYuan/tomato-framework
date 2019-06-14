package com.tomato.framework.plugin.rmi.register;

import java.rmi.Remote;

public interface Register {
    
    <T extends Remote> void register(String address, int port);
}
