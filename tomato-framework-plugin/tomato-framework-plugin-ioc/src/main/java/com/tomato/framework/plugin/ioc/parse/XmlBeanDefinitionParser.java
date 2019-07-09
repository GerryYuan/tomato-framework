package com.tomato.framework.plugin.ioc.parse;

import com.tomato.framework.plugin.ioc.factory.DefaultListableBeanFactory;
import com.tomato.framework.plugin.ioc.utils.Dom4jReader;
import java.io.InputStream;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;

public class XmlBeanDefinitionParser {
    
    private DefaultListableBeanFactory beanFactory;
    
    public void parser(DefaultListableBeanFactory beanFactory, InputStream inputStream) {
        this.beanFactory = beanFactory;
        //2.根据Bean信息，构造BeanDefinition对象实例
        //  2.1 bean标签的解析（class，id，initMethod）
        //  2.2 bean的子标签Property（name，value-string，value-reference）
        Document document = Dom4jReader.reader(inputStream);
        parseBeanDefinitions(document.getRootElement());
    }
    
    private void parseBeanDefinitions(Element element) {
        List<Element> elements = element.elements();
        elements.forEach(e -> {
            //判断是不是bean标签，如果是，则走bean标签，否则走其他标签
            parseBeanDefinition(e);
        });
    }
    
    private void parseBeanDefinition(Element element) {
        String id = element.attributeValue("id");
        String clazz = element.attributeValue("class");
        try {
            Class<?> classType = Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
    private void parseOther(Element element) {
    }
}
