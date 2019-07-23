package com.tomato.framework.plugin.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RequestMapping {
    
    String value();
}
