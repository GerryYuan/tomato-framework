package com.tomato.framework.plugin.ioc.parse;

import com.tomato.framework.plugin.ioc.definition.BeanDefinition;
import com.tomato.framework.plugin.ioc.definition.BeanDefinition.BeanDefinitionBuilder;
import com.tomato.framework.plugin.ioc.definition.PropertyValue;
import com.tomato.framework.plugin.ioc.definition.PropertyValue.PropertyValueBuilder;
import com.tomato.framework.plugin.ioc.utils.ReflectUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.dom4j.Element;

/**
 * @author yuanguohua
 */
public class DocumentBeanDefinitionParser {
    
    private List<TypeConverter> converters = new ArrayList<>(30);
    
    public DocumentBeanDefinitionParser() {
        converters.add(new StringTypeConverter());
        converters.add(new IntegerTypeConverter());
    }
    
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
        PropertyValueBuilder builder = PropertyValue.builder().name(name);
        //如果value值不是空，则认为是除了引用类型的属性值之外的类型
        //否则，如果ref不是空，则认为是引用类型
        if (Objects.nonNull(value)) {
            //需要判断type是什么类型，然后new相应的TypeValue
            Class<?> type = ReflectUtils.getTypeByFieldName(beanDefinition.getClazz(), name);
            TypeConverter converter = converters.stream().filter(c -> c.isType(type)).findFirst()
                .orElse(null);
            if (Objects.isNull(converter)) {
                return;
            }
            builder.value(converter.convert(value));
        } else if (Objects.nonNull(ref)) {
            builder.value(new ReferenceTypeConverter(ref));
        }
        beanDefinition.addPropertyValue(builder.build());
    }
    
}
