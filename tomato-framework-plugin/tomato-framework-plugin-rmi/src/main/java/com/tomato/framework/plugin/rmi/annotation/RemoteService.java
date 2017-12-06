package com.tomato.framework.plugin.rmi.annotation;

import com.tomato.framework.plugin.rmi.enums.ServiceType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.rmi.registry.Registry;

/**
 * @author Created by gerry
 * @version 1.0, 2017-04-07-18:48
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RemoteService {

    ServiceType serviceType() default ServiceType.HTTP;

    Class<?> serviceInterface();

    int port() default Registry.REGISTRY_PORT;

    String url();
}
