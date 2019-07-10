package com.tomato.framework.plugin.ioc.parse;

/**
 * @author yuanguohua
 */
public class TypedStringValue implements TypedValue {
    
    private String value;
    
    private Class<?> classType;
    
    public TypedStringValue(String value) {
        this.value = value;
    }
    
    public TypedStringValue(String value, Class<?> classType) {
        this.value = value;
        this.classType = classType;
    }
    
    @Override
    public boolean isReference() {
        return false;
    }
    
    @Override
    public BeanPropertyTypedValue getTypedValue() {
        return new BeanPropertyTypedValue(value, classType);
    }
}
