package com.tomato.framework.test.cache;

import com.tomato.framework.test.TestsConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

    @Test
    public void localCache() throws Exception {
    }
}
