package com.tomato.framework.plugin.ioc.test;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-07-11:19
 */
public class RiceFactory implements HotelFactory {
    
    @Override
    public Food create() {
        return new Rice();
    }
}
