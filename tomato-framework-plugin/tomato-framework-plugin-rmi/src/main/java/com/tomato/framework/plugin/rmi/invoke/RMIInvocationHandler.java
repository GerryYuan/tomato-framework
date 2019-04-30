package com.tomato.framework.plugin.rmi.invoke;

import com.tomato.framework.plugin.rmi.RMIRequest;
import com.tomato.framework.plugin.rmi.transport.Transport;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RMIInvocationHandler implements InvocationHandler {
    
    private Transport transport;
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RMIRequest rmiRequest = new RMIRequest();
        rmiRequest.setClassName(method.getDeclaringClass().getName());
        rmiRequest.setMethodName(method.getName());
        rmiRequest.setParamters(args);
        return transport.send(rmiRequest);
    }
}
