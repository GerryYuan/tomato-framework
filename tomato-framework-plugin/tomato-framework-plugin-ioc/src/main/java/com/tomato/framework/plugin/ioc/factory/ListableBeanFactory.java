package com.tomato.framework.plugin.ioc.factory;

import java.util.List;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-06-17:16
 */
public interface ListableBeanFactory extends BeanFactory {

    <T> List<T> getBeans();
    
}
