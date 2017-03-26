package com.tomato.framework.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Created by gerry
 * @version 1.0, 2017-03-24-18:10
 * @since com.hujiang 1.0.0
 */
@RunWith(SpringRunner.class)
@Configuration
@ComponentScan(basePackages = {"com.tomato.framework"})
@PropertySource("application-test.properties")
public class CacheTest {

    @Test
    public void test() {
        System.out.print("");
    }

}
