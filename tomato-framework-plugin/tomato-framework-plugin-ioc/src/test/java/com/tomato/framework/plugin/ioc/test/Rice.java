package com.tomato.framework.plugin.ioc.test;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-07-11:20
 */
public class Rice implements Food {
    
    @Override
    public void eat() {
        System.out.println("吃米饭");
    }
}
