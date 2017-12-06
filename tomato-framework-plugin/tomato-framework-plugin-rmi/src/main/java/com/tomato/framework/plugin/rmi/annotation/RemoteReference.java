package com.tomato.framework.plugin.rmi.annotation;

import com.tomato.framework.plugin.rmi.enums.ServiceType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2017-04-09-23:34
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Component
public @interface RemoteReference {

    String url();

    Class<?> serviceInterface();

    ServiceType serviceType() default ServiceType.HTTP;

}
