package com.tomato.framework.plugin.rmi.annotation;

import com.tomato.framework.plugin.rmi.enums.ServiceType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.rmi.registry.Registry;

/**
 * @author Created by gerry
 * @version 1.0, 2017-04-07-18:48
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RemoteService {

    ServiceType serviceType() default ServiceType.HTTP;

    Class<?> serviceInterface();

    int port() default Registry.REGISTRY_PORT;

    String url();
}
