package com.tomato.framework.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Created by gerry
 * @version 1.0, 2017-03-18-16:36
 * @since com.hujiang 1.0.0
 */
@RunWith(SpringRunner.class)
@PropertySource("")
public class FrameworkTest {

    @Test
    public void test(){
        System.out.printf("ddd");
    }

}
