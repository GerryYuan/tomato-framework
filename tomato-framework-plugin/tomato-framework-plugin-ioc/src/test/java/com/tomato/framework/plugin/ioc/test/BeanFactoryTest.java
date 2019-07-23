package com.tomato.framework.plugin.ioc.test;

import com.tomato.framework.plugin.ioc.factory.BeanFactory;
import com.tomato.framework.plugin.ioc.factory.DefaultListableBeanFactory;
import com.tomato.framework.plugin.ioc.reource.ClassPathResource;
import com.tomato.framework.plugin.ioc.reource.Resource;
import com.tomato.framework.plugin.ioc.utils.ReflectUtils;
import org.junit.Test;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-08-21:44
 */
public class BeanFactoryTest {
    
    @Test
    public void getBean() {
        //读取配置文件
        String location = "classpath:beans.xml";
        Resource resource = new ClassPathResource(location);
        BeanFactory beanFactory = new DefaultListableBeanFactory(resource);
        User user = beanFactory.getBean("student");
        System.out.println(user);
    }
    
    @Test
    public void test() {
        User user = new User();
        user.setName("zengkeyan");
        Course course = new Course();
        course.setName("spring");
        course.setAge(18);
//        course.setUser(user);
        //第一步，实例化user，放入三级缓存，set相关属性
        //第二步，发现user依赖course，去实例化course，放入三级缓存，set相关属性
        //第三步，发现course依赖user，去查找user
        //第四步，从一级缓存找，没有，二级缓存找，没有，三级缓存ObjectFactory.getObject获取到实例化的user，放入二级缓存，删除三级缓存
        //第五步，course实例进行初始化，完成之后放入一级缓存，删除二三级缓存
        //第六步，返回到user的初始化过程，完成后放入一级缓存，删除二三级缓存
        ReflectUtils.setProperty(user, "course", course);
        System.out.println(user);
        
    }
}
