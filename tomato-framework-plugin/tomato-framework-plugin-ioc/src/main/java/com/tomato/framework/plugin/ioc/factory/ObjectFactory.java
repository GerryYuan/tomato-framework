package com.tomato.framework.plugin.ioc.factory;

@FunctionalInterface
public interface ObjectFactory<T> {
    
    T get();
}
