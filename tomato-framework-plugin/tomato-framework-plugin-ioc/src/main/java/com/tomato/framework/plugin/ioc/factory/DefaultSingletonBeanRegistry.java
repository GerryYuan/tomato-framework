package com.tomato.framework.plugin.ioc.factory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanFactory {
    
    private Map<String, Object> singletonBeans = new ConcurrentHashMap<>(256);
    
    private final Map<String, Object> earlySingletonBeans = new ConcurrentHashMap<>(16);
    
    @Override
    public <T> T getSingleton(String beanName) {
        synchronized (this.singletonBeans) {
            Object bean = this.singletonBeans.get(beanName);
            if (!Objects.isNull(bean)) {
                return (T) bean;
            }
            bean = this.earlySingletonBeans.get(beanName);
            if (!Objects.isNull(bean)) {
                return (T) bean;
            }
            return null;
        }
    }
    
    @Override
    public <T> void addSingleton(String beanName, T obj) {
        synchronized (this.singletonBeans) {
            singletonBeans.put(beanName, obj);
            earlySingletonBeans.put(beanName, obj);
        }
    }
    
    @Override
    public <T> T getBean(String name) {
        return getSingleton(name);
    }
}
