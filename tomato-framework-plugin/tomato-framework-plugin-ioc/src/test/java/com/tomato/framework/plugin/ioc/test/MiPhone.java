package com.tomato.framework.plugin.ioc.test;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-07-11:58
 */
public class MiPhone implements Phone {
    
    @Override
    public void call() {
        System.out.println("小米手机打电话");
    }
}
