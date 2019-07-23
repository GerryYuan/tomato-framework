package com.tomato.framework.plugin.ioc.factory;

import com.tomato.framework.plugin.ioc.reource.Resource;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-06-17:21
 */
public class XmlBeanFactory extends DefaultListableBeanFactory{
    
    public XmlBeanFactory(Resource resource) {
        super(resource);
    }
}
