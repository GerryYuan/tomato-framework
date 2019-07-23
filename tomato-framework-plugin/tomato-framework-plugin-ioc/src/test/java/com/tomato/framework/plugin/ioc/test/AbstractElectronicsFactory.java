package com.tomato.framework.plugin.ioc.test;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-07-11:57
 */
public abstract class AbstractElectronicsFactory {

    abstract Phone createPhone();
    
    abstract Display createDisplay();
    
}
