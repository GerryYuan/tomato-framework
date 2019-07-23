package com.tomato.framework.plugin.ioc.factory;

import com.tomato.framework.plugin.ioc.definition.BeanDefinition;

public abstract class AbstractBeanFactory implements BeanFactory {
    
    public abstract void setProperty(Object bean, BeanDefinition beanDefinition);
}
