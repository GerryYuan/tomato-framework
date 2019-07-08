package com.tomato.framework.plugin.ioc.test;

import com.tomato.framework.plugin.ioc.factory.BeanFactory;
import com.tomato.framework.plugin.ioc.factory.DefaultListableBeanFactory;
import com.tomato.framework.plugin.ioc.reource.ClassPathResource;
import com.tomato.framework.plugin.ioc.reource.Resource;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-08-21:44
 */
public class BeanFactoryTest {
    
    public void test(){
        //读取配置文件
        String location = "classpath:beans.xml";
        Resource resource = new ClassPathResource(location);
        BeanFactory beanFactory = new DefaultListableBeanFactory();
        User user = beanFactory.getBean("user");
        System.out.println(user);
    }
}
