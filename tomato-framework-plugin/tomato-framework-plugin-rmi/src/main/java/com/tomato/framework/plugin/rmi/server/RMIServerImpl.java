package com.tomato.framework.plugin.rmi.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RMIServerImpl implements RMIServer {
    
    private static final ExecutorService threadPool = new ThreadPoolExecutor(10, 10,
        0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    
    @Override
    public void publish(Object service, int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("创建socket端口{}，发布服务service{}", port, service);
            RMIRegisterConfig.bind(service);
            while (true) {
                //循环监听serversocket产生的输入流，然后读取调用相应的方法
                Socket socket = serverSocket.accept();
                threadPool.execute(new ServerProcessHanlder(socket, service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
