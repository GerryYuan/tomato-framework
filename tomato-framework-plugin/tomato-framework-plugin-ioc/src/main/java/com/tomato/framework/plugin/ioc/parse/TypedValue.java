package com.tomato.framework.plugin.ioc.parse;

public interface TypedValue {
    
    boolean isReference();
    
    BeanPropertyTypedValue getTypedValue();
}
