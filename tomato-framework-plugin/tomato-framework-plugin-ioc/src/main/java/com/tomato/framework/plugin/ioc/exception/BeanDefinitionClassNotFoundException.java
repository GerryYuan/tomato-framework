package com.tomato.framework.plugin.ioc.exception;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-16:13
 */
public class BeanDefinitionClassNotFoundException extends RuntimeException {
    
    public BeanDefinitionClassNotFoundException(String msg) {
        super(msg);
    }
    
    public BeanDefinitionClassNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public BeanDefinitionClassNotFoundException(Throwable cause) {
        super(cause);
    }
    
}
