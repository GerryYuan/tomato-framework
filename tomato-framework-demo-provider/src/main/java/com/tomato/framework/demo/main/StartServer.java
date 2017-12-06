package com.tomato.framework.demo.main;

import com.tomato.framework.core.main.Starter;
import com.tomato.framework.plugin.rpc.annotation.EnableDubboConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.tomato.framework.demo"})
@EnableDubboConfiguration
public class StartServer {

    public static void main(String[] args) {
        Starter.run(StartServer.class, args);
    }


}
