package com.tomato.framework.plugin.ioc.parse;

public class ReferenceTypeConverter extends AbstractTypedValue implements TypeConverter {
    
    public ReferenceTypeConverter(Object value) {
        super(value);
    }
    
    public ReferenceTypeConverter() {
    }
    
    @Override
    public boolean isType(Class<?> clazz) {
        return false;
    }
    
    @Override
    public TypedValue convert(String source) {
        return new ReferenceTypeConverter(source);
    }
    
    @Override
    public boolean isRef() {
        return true;
    }
}
