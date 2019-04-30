package com.tomato.framework.plugin.rmi.proxy;

import com.tomato.framework.plugin.rmi.invoke.RMIInvocationHandler;
import com.tomato.framework.plugin.rmi.transport.Transport;
import java.lang.reflect.Proxy;

public interface ProxyClient {
    
    static Object createProxy(Class<?> clazz, String host, int port) {
        return Proxy
            .newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new RMIInvocationHandler(new Transport(host, port)));
    }
}
