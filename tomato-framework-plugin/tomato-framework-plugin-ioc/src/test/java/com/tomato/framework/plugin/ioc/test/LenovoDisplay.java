package com.tomato.framework.plugin.ioc.test;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-07-12:00
 */
public class LenovoDisplay implements Display {
    
    @Override
    public void display() {
        System.out.println("联想显示器");
    }
}
