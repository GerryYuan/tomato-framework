package com.tomato.framework.plugin.ioc.parse;

public interface TypedValue {
    
    boolean isRef();
    
    <T> T getValue();
}
