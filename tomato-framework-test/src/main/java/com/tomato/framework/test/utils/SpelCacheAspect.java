package com.tomato.framework.test.utils;

import com.tomato.framework.core.util.SpelUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Aspect
@Component
@Slf4j
public class SpelCacheAspect {

    @Around(" @annotation(rc) ")
    public Object aroundMethod(ProceedingJoinPoint jp, SpelCache rc) throws Throwable {
        String key = SpelUtils.parseKey(rc.key(), SpelUtils.getMethod(jp), jp.getArgs());
        Assert.notNull(key, "non null key required");
        log.debug("remote cache annotation key: " + key);
        return jp.proceed();
    }
}
