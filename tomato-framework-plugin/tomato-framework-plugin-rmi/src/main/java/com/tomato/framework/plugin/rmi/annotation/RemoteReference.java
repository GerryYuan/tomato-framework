package com.tomato.framework.plugin.rmi.annotation;

import com.tomato.framework.plugin.rmi.enums.ServiceType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2017-04-09-23:34
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface RemoteReference {

    String url();

    Class<?> serviceInterface();

    ServiceType serviceType() default ServiceType.HTTP;

}
