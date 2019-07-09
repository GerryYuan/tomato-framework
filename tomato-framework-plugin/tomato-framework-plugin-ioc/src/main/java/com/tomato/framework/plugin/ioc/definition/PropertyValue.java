package com.tomato.framework.plugin.ioc.definition;

import lombok.Builder;

@Builder
public class PropertyValue {
    
    private String name;
    
    private String value;
    
    private Object ref;
    
}
