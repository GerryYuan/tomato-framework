package com.tomato.framework.plugin.ioc.exception;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-16:13
 */
public class BeanNotFoundException extends RuntimeException {
    
    public BeanNotFoundException(String msg) {
        super(msg);
    }
    
    public BeanNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public BeanNotFoundException(Throwable cause) {
        super(cause);
    }
    
}
