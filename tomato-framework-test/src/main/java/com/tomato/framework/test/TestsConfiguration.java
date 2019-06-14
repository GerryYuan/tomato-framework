package com.tomato.framework.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.tomato.framework"})
@PropertySource("application.properties")
public class TestsConfiguration {
}
