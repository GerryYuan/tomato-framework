package com.tomato.framework.demo.main;

import com.tomato.framework.core.main.Starter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({ "com.tomato.framework.demo" })
public class StartApp {

	public static void main(String[] args) {
		Starter.run(StartApp.class, args);
	}
}
