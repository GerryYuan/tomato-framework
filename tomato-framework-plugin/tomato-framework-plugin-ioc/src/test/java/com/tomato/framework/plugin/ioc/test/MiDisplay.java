package com.tomato.framework.plugin.ioc.test;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-07-12:00
 */
public class MiDisplay implements Display {
    
    @Override
    public void display() {
        System.out.println("小米显示器");
    }
}
