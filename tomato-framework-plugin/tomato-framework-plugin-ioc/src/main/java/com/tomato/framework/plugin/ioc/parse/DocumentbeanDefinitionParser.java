package com.tomato.framework.plugin.ioc.parse;

import com.tomato.framework.plugin.ioc.definition.BeanDefinition;
import com.tomato.framework.plugin.ioc.definition.BeanDefinition.BeanDefinitionBuilder;
import org.dom4j.Element;

/**
 * @author yuanguohua
 */
public class DocumentbeanDefinitionParser {
    
    public BeanDefinitionHolder parseBeanDefinition(Element element) {
        String id = element.attributeValue("id");
        String clazz = element.attributeValue("class");
        BeanDefinitionBuilder builder = BeanDefinition.builder().beanName(id).clazz(clazz);
        String initMethod = element.attributeValue("init-method");
        if (initMethod != null) {
            builder.initMethod(initMethod);
        }
        return new BeanDefinitionHolder(id, builder.build());
    }
    
}
