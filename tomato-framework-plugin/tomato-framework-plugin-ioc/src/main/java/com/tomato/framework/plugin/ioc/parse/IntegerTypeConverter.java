package com.tomato.framework.plugin.ioc.parse;

public class IntegerTypeConverter extends AbstractTypedValue implements TypeConverter {
    
    public IntegerTypeConverter(Object value) {
        super(value);
    }
    
    public IntegerTypeConverter() {
    }
    
    @Override
    public TypedValue convert(String source) {
        return new IntegerTypeConverter(Integer.valueOf(source));
    }
    
    @Override
    public boolean isType(Class<?> clazz) {
        return clazz == Integer.class;
    }
    
    @Override
    public boolean isRef() {
        return false;
    }
    
}
