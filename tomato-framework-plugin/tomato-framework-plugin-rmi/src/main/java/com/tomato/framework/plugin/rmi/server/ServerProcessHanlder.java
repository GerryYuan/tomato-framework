package com.tomato.framework.plugin.rmi.server;

import com.tomato.framework.plugin.rmi.RMIRequest;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerProcessHanlder implements Runnable {
    
    private Socket socket;
    
    private Object service;
    
    /**
     * 服务端发布的服务
     */
    private Map<String,Object> services;
    
    public ServerProcessHanlder(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }
    
    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            //反序列化
            RMIRequest rpcRequest = (RMIRequest) objectInputStream.readObject();
            Object result = invoke(rpcRequest);
            //将结果返回给客户端
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private Object invoke(RMIRequest rpcRequest)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("服务端开始调用------");
        Object[] parameters = rpcRequest.getParamters();
        Class<?>[] parameterTypes = Arrays.stream(parameters).map(s -> s.getClass()).toArray(Class[]::new);
        //从Map中获得Service（根据客户端请求的ServiceName，获得相应的服务），依旧是通过反射发起调用
        Object service = services.get(rpcRequest.getClassName());
        Method method = service.getClass().getMethod(rpcRequest.getMethodName(), parameterTypes);
        return method.invoke(service, parameters);
    }
}
