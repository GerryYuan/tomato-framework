package com.tomato.framework.plugin.ioc.parse;

import com.tomato.framework.plugin.ioc.definition.BeanDefinition;
import com.tomato.framework.plugin.ioc.exception.BeanDefinitionClassNotFoundException;
import lombok.Getter;

@Getter
public class BeanDefinitionHolder {
    
    private String beanName;
    
    private Class<?> classType;
    
    private BeanDefinition beanDefinition;
    
    public BeanDefinitionHolder(String beanName, BeanDefinition beanDefinition) {
        this.beanName = beanName;
        this.beanDefinition = beanDefinition;
        try {
            this.classType = Class.forName(beanDefinition.getClazz());
        } catch (ClassNotFoundException e) {
            throw new BeanDefinitionClassNotFoundException(e);
        }
    }
}
