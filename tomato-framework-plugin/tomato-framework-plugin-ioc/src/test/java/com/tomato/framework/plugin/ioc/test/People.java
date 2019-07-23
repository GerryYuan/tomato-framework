package com.tomato.framework.plugin.ioc.test;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-07-11:23
 */
public class People {
    
    public static void main(String[] args) {
        HotelFactory hotelFactory = new ChickenFactory();
        hotelFactory.create().eat();
    
        AbstractElectronicsFactory lenovoFactory = new LenovoFactory();
        lenovoFactory.createPhone().call();
        lenovoFactory.createDisplay().display();
    
        AbstractElectronicsFactory miFactory = new MiFactory();
        miFactory.createPhone().call();
        miFactory.createDisplay().display();
        Car car = Car.builder().wheel("wheel").body("body").build();
        System.out.println(car);
    }
}
