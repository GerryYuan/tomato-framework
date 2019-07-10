package com.tomato.framework.plugin.ioc.factory;

import com.tomato.framework.plugin.ioc.definition.BeanDefinition;
import com.tomato.framework.plugin.ioc.exception.BeanNotFoundException;
import com.tomato.framework.plugin.ioc.parse.XmlBeanDefinitionParser;
import com.tomato.framework.plugin.ioc.reource.Resource;
import com.tomato.framework.plugin.ioc.utils.ReflectUtils;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-06-17:15
 */
public class DefaultListableBeanFactory extends AbstractBeanFactory {
    
    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();
    
    private Map<String, Object> beans = new ConcurrentHashMap<>();
    
    public DefaultListableBeanFactory(Resource resource) {
        //1.读取配置文件获取Bean
        InputStream inputStream = resource.getInputStream();
        //Xml解析，组装成BeanDefinition
        XmlBeanDefinitionParser xmlBeanDefinitionParser = new XmlBeanDefinitionParser();
        xmlBeanDefinitionParser.parser(this, inputStream);
        //转换BeanDifinition到Bean实例
    }
    
    public void addBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitions.put(beanName, beanDefinition);
    }
    
    //3.把BeanDefinition放入集合中
    //4.根据BeanDefinition构造Bean实例
    //5.把Bean实例放入到集合中
    @Override
    public <T> T getBean(String name) {
        Object bean = beans.get(name);
        if (Objects.nonNull(bean)) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = beanDefinitions.get(name);
        if (Objects.isNull(beanDefinition)) {
            throw new BeanNotFoundException("bean " + name + " is not found");
        }
        Object instance = ReflectUtils.createObject(beanDefinition.getClazz());
        this.setProperty(instance, beanDefinition);
        return null;
    }
    
    @Override
    public void setProperty(Object bean, BeanDefinition beanDefinition) {
        beanDefinition.getPropertyValues().forEach(propertyValue -> {
            //属性名称，然后调用bean对象的属性的set方法，值为value；
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            //根据obj value的类型处理不同的逻辑
        });
    }
}
