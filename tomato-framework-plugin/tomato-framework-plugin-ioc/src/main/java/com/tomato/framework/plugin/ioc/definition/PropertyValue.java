package com.tomato.framework.plugin.ioc.definition;

import com.tomato.framework.plugin.ioc.parse.TypedValue;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PropertyValue {
    
    private String name;
    
    private TypedValue value;
    
}
