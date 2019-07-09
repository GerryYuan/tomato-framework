package com.tomato.framework.plugin.ioc.definition;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-06-17:23
 */
@Builder
@Getter
public class BeanDefinition {
    
    private String beanName;
    
    private String clazz;
    
    private String initMethod;
    
    private List<PropertyValue> propertyValues;
    
}
