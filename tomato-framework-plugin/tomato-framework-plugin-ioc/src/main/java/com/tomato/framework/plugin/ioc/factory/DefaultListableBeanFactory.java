package com.tomato.framework.plugin.ioc.factory;

import com.tomato.framework.plugin.ioc.reource.Resource;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-06-17:15
 */
public class DefaultListableBeanFactory implements BeanFactory {
    
    public DefaultListableBeanFactory(Resource resource) {
        //1.读取配置文件获取Bean
    }
    
    //2.根据Bean信息，构造BeanDefinition对象实例
    //  2.1 bean标签的解析（class，id，initMethod）
    //  2.2 bean的子标签Property（name，value-string，value-reference）
    //3.把BeanDefinition放入集合中
    //4.根据BeanDefinition构造Bean实例
    //5.把Bean实例放入到集合中
    @Override
    public <T> T getBean(String name) {
        return null;
    }
}
