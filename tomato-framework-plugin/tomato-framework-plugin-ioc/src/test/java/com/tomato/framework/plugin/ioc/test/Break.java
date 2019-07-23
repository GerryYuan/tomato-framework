package com.tomato.framework.plugin.ioc.test;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-07-11:20
 */
public class Break implements Food {
    
    @Override
    public void eat() {
        System.out.println("吃面包");
    }
}
