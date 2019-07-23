package com.tomato.framework.plugin.ioc.factory;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-06-17:11
 */
public interface BeanFactory {
    
    <T> T getBean(String name);
    
}
