package com.tomato.framework.test;

import com.tomato.framework.core.util.IdUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Created by gerry
 * @version 1.0, 2017-03-18-16:36
 * @since com.hujiang 1.0.0
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.tomato.framework"})
@PropertySource("application-test.properties")
public class IdUtilsTest {

    @Test
    public void test(){
        System.out.print(new IdUtils(0,0).nextId());
    }

}
