package com.tomato.framework.plugin.ioc.factory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuanguohua
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanFactory {
    
    private Map<String, Object> singletonBeans = new ConcurrentHashMap<>(256);
    
    private final Map<String, Object> earlySingletonBeans = new ConcurrentHashMap<>(16);
    
    private final Map<String, ObjectFactory<?>> singletonFactories = new ConcurrentHashMap<>(256);
    
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
            ObjectFactory<?> objectFactory = singletonFactories.get(beanName);
            if (!Objects.isNull(objectFactory)) {
                bean = objectFactory.get();
                earlySingletonBeans.put(beanName, bean);
                singletonFactories.remove(beanName);
            }
            return (T) bean;
        }
    }
    
    @Override
    public <T> void addSingleton(String beanName, T obj) {
        synchronized (this.singletonBeans) {
            if (!this.singletonBeans.containsKey(beanName)) {
                singletonBeans.put(beanName, obj);
                earlySingletonBeans.remove(beanName);
                singletonFactories.remove(beanName);
            }
        }
    }
    
    @Override
    public void addSingletonFactory(String beanName, ObjectFactory<?> obj) {
        synchronized (this.singletonBeans) {
            if (!this.singletonBeans.containsKey(beanName)) {
                singletonFactories.put(beanName, obj);
                earlySingletonBeans.remove(beanName);
            }
        }
    }
    
    @Override
    public <T> T getBean(String name) {
        return getSingleton(name);
    }
}
