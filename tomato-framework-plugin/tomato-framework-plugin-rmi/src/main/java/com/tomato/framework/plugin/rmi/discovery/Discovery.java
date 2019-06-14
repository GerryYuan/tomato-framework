package com.tomato.framework.plugin.rmi.discovery;

import java.rmi.Remote;

public interface Discovery {
    
    <T extends Remote> T getBean(Class<T> clazz);
}
