package com.tomato.framework.plugin.ioc.factory;

import com.tomato.framework.plugin.ioc.definition.BeanDefinition;
import com.tomato.framework.plugin.ioc.exception.BeanNotFoundException;
import com.tomato.framework.plugin.ioc.parse.TypedValue;
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
    
    private SingletonBeanFactory singletonBeanFactory = new DefaultSingletonBeanRegistry();
    
    public DefaultListableBeanFactory(Resource resource) {
        //1.读取配置文件获取Bean
        InputStream inputStream = resource.getInputStream();
        //Xml解析，组装成BeanDefinition
        XmlBeanDefinitionParser xmlBeanDefinitionParser = new XmlBeanDefinitionParser();
        xmlBeanDefinitionParser.parser(this, inputStream);
        //转换BeanDifinition到Bean实例
    }
    
    //3.把BeanDefinition放入集合中
    public void addBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitions.put(beanName, beanDefinition);
    }
    
    @Override
    public <T> T getBean(String name) {
        Object bean = singletonBeanFactory.getBean(name);
        if (Objects.nonNull(bean)) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = beanDefinitions.get(name);
        if (Objects.isNull(beanDefinition)) {
            throw new BeanNotFoundException("bean " + name + " is not found");
        }
        /**
         * a)、构建BeanFactory工厂-获取beanDefinition
         * b)、然后获取实例化bean，给bean进行属性赋值，依赖注入（循环依赖问题，通过三级缓存解决）
         * c)、初始化bean实例
         */
        //4.根据BeanDefinition构造Bean实例，并且把bean放入三级缓存中
        Object instance = doCreateBean(beanDefinition.getBeanName(), beanDefinition.getClazz());
        //设置属性值，进行属性依赖注入
        this.setProperty(instance, beanDefinition);
        //初始化bean对象
        initMethods(instance, beanDefinition.getInitMethod());
        //5.把Bean实例放入到集合中
        singletonBeanFactory.addSingleton(name, instance);
        return (T) instance;
    }
    
    private Object doCreateBean(String beanName, String clazz) {
        Object bean = ReflectUtils.newInstance(clazz);
        final Object finalBean = bean;
        singletonBeanFactory.addSingletonFactory(beanName, () -> finalBean);
        return finalBean;
    }
    
    private void initMethods(Object instance, String methodName) {
        if (instance instanceof InitializingBean) {
            ((InitializingBean) instance).afterPropertiesSet();
        } else {
            ReflectUtils.invokeMethod(instance, methodName);
        }
    }
    
    @Override
    public void setProperty(Object bean, BeanDefinition beanDefinition) {
        beanDefinition.getPropertyValues().forEach(propertyValue -> {
            //属性名称，然后调用bean对象的属性的set方法，值为value；
            String name = propertyValue.getName();
            TypedValue typedValue = propertyValue.getValue();
            //根据obj value的类型处理不同的逻辑
            if (typedValue.isRef()) {
                ReflectUtils.setProperty(bean, name, this.getBean(typedValue.getValue()));
            } else {
                ReflectUtils.setProperty(bean, name, typedValue.getValue());
            }
        });
    }
}
