package com.tomato.framework.plugin.ioc.factory;

import com.tomato.framework.plugin.ioc.definition.BeanDefinition;
import com.tomato.framework.plugin.ioc.parse.XmlBeanDefinitionParser;
import com.tomato.framework.plugin.ioc.reource.Resource;
import java.io.InputStream;
import java.util.Map;
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
        return (T) beans.get(name);
    }
}
