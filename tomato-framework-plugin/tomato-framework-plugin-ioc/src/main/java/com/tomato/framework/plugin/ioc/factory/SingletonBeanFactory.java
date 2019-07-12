package com.tomato.framework.plugin.ioc.factory;

public interface SingletonBeanFactory extends BeanFactory {
    
    <T> T getSingleton(String beanName);
    
    <T> void addSingleton(String beanName, T obj);
}
