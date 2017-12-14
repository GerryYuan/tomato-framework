package com.tomato.framework.plugin.cache.aspect;

import com.tomato.framework.core.exception.SysException;
import com.tomato.framework.core.util.EmptyUtils;
import com.tomato.framework.core.util.SpelUtils;
import com.tomato.framework.plugin.cache.annotation.RemoteCache;
import com.tomato.framework.plugin.cache.ops.RemoteCacheManager;
import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RemoteCacheAspect {

    @Around(" @annotation(rc) ")
    public Object aroundMethod(ProceedingJoinPoint jp, RemoteCache rc) {
        Method method = SpelUtils.getMethod(jp);
        Object[] args = jp.getArgs();
        String key = (String) SpelUtils.parseKey(rc.key(), method, args);
        log.debug("remote cache annotation key: " + key);
        return RemoteCacheManager.getInstance().vget(key, () -> {
            try {
                return jp.proceed();
            } catch (Throwable throwable) {
                throw new SysException(throwable.getMessage());
            }
        }, rc.timeUnit().toSeconds(rc.expire()));
    }

}
