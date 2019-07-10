package com.tomato.framework.plugin.ioc.parse;

import lombok.Data;

/**
 * @author yuanguohua
 */
@Data
public class TypedReferenceValue implements TypedValue {
    
    private String ref;
    
    public TypedReferenceValue(String ref) {
        this.ref = ref;
    }
    
    @Override
    public boolean isReference() {
        return true;
    }
    
    @Override
    public BeanPropertyTypedValue getTypedValue() {
        return new BeanPropertyTypedValue(ref, null);
    }
}
