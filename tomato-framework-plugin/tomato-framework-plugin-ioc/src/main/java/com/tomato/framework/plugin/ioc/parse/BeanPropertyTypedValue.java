package com.tomato.framework.plugin.ioc.parse;

import lombok.Getter;

@Getter
public class BeanPropertyTypedValue {
    
    private String name;
    
    private Class<?> clazz;
    
    public BeanPropertyTypedValue(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
    }
}
