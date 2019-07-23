package com.tomato.framework.plugin.ioc.test;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-07-12:01
 */
public class MiFactory extends AbstractElectronicsFactory {
    
    @Override
    Phone createPhone() {
        return new MiPhone();
    }
    
    @Override
    Display createDisplay() {
        return new MiDisplay();
    }
}
