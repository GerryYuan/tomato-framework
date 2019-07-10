package com.tomato.framework.plugin.ioc.parse;

import com.tomato.framework.plugin.ioc.definition.BeanDefinition;
import com.tomato.framework.plugin.ioc.definition.BeanDefinition.BeanDefinitionBuilder;
import com.tomato.framework.plugin.ioc.definition.PropertyValue;
import com.tomato.framework.plugin.ioc.definition.PropertyValue.PropertyValueBuilder;
import com.tomato.framework.plugin.ioc.utils.ReflectUtils;
import java.util.List;
import java.util.Objects;
import org.dom4j.Element;

/**
 * @author yuanguohua
 */
public class DocumentBeanDefinitionParser {
    
    public BeanDefinitionHolder parseBeanDefinition(Element element) {
        String id = element.attributeValue("id");
        String clazz = element.attributeValue("class");
        BeanDefinitionBuilder builder = BeanDefinition.builder().beanName(id).clazz(clazz);
        String initMethod = element.attributeValue("init-method");
        if (initMethod != null) {
            builder.initMethod(initMethod);
        }
        List<Element> elements = element.elements();
        BeanDefinition beanDefinition = builder.build();
        elements.forEach(e -> parsePropertyValue(beanDefinition, e));
        return new BeanDefinitionHolder(id, beanDefinition);
    }
    
    private void parsePropertyValue(BeanDefinition beanDefinition, Element e) {
        // 获取name属性
        String name = e.attributeValue("name");
        // 获取value属性
        String value = e.attributeValue("value");
        // 获取ref属性
        String ref = e.attributeValue("ref");
        if (Objects.nonNull(value) && Objects.nonNull(ref)) {
            return;
        }
        PropertyValueBuilder propertyValueBuilder = PropertyValue.builder().name(name);
        if (Objects.nonNull(value)) {
            Class<?> type = ReflectUtils.getTypeByFieldName(beanDefinition.getClazz(), name);
            propertyValueBuilder.value(new TypedStringValue(value, type));
        } else if (Objects.nonNull(ref)) {
            propertyValueBuilder.value(new TypedReferenceValue(ref));
        }
        beanDefinition.addPropertyValue(propertyValueBuilder.build());
    }
    
}
