package com.tomato.framework.plugin.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Controller {
    
    String value();
}
