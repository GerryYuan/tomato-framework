package com.tomato.framework.test.utils;

import com.tomato.framework.core.util.SpelUtils;
import com.tomato.framework.test.TestsConfiguration;
import org.junit.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author gerry
 * @version 1.0, 2017-12-14 11:48
 * @since com.hujiang 1.0.0
 */
public class SeplUtilsTest {

    ApplicationContext ctx = new AnnotationConfigApplicationContext(TestsConfiguration.class);

    @Test
    public void parseKey() {
        SpelService spelService = ctx.getBean(SpelService.class);
        System.out.println(spelService.getName("hello"));
    }
}
