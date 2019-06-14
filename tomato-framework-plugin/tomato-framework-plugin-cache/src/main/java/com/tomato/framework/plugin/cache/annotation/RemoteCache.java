package com.tomato.framework.plugin.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author gerry
 * @version 1.0, 2017-12-14 10:51
 * @since com.hujiang 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RemoteCache {

    String key() default "";

    long expire() default 1;

    TimeUnit timeUnit() default TimeUnit.MINUTES;

}
