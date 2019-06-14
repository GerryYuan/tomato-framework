package com.tomato.framework.core.main;

import org.springframework.boot.ResourceBanner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.io.ClassPathResource;

public class Starter {

	public static void run(Class<?> clazz, String[] args) {
		ResourceBanner rb = new ResourceBanner(new ClassPathResource("banner.txt"));
		new SpringApplicationBuilder().sources(clazz).banner(rb).run(args);
	}

}
