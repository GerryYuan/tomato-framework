package com.tomato.framework.plugin.ioc.parse;

public class StringTypeConverter extends AbstractTypedValue implements TypeConverter {
    
    public StringTypeConverter(Object value) {
        super(value);
    }
    
    public StringTypeConverter() {
    }
    
    @Override
    public boolean isType(Class<?> clazz) {
        return clazz == String.class;
    }
    
    @Override
    public TypedValue convert(String source) {
        return new StringTypeConverter(source);
    }
    
    @Override
    public boolean isRef() {
        return false;
    }
}
