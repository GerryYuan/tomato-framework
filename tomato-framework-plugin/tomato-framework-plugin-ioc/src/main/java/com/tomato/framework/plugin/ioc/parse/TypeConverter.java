package com.tomato.framework.plugin.ioc.parse;

public interface TypeConverter {
    
    boolean isType(Class<?> clazz);
    
    TypedValue convert(String source);
}
