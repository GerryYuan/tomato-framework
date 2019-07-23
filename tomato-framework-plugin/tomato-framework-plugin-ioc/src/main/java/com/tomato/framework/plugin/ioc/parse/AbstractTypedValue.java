package com.tomato.framework.plugin.ioc.parse;

public abstract class AbstractTypedValue implements TypedValue {
    
    private Object value;
    
    public AbstractTypedValue() {
    }
    
    public AbstractTypedValue(Object value) {
        this.value = value;
    }
    
    @Override
    public <T> T getValue() {
        return (T) this.value;
    }
    
}
