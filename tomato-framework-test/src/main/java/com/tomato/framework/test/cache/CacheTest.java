package com.tomato.framework.test.cache;

import com.tomato.framework.core.config.SpringApplicationContext;
import com.tomato.framework.test.TestsConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by gerry
 */
@Slf4j
public class CacheTest {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(TestsConfiguration.class);

    @Test
    public void remoteCache() throws Exception {
        CacheAnnotationService cacheAnnotationService = ctx.getBean(CacheAnnotationService.class);
        log.info(cacheAnnotationService.testRemoteCache("localCache", true).getName());
    }


}
