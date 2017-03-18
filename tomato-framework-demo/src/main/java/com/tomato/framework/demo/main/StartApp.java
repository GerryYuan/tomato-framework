package com.tomato.framework.demo.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.tomato.framework.rest.main.Starter;

@SpringBootApplication
@ComponentScan({ "com.tomato.framework.demo" })
public class StartApp {

	public static void main(String[] args) {
		Starter.run(StartApp.class, args);
	}
}
