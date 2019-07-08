package com.tomato.framework.plugin.ioc.test;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-07-19:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Car {
    
    private String wheel;
    
    private String body;
    
    public static Car.CarBuilder builder() {
        return new CarBuilder();
    }
    
    public static class CarBuilder {
        
        private Car car;
        
        private CarBuilder() {
            car = new Car();
        }
        
        public CarBuilder wheel(String wheel) {
            this.car.wheel = wheel;
            return this;
        }
        
        public CarBuilder body(String body) {
            this.car.body = body;
            return this;
        }
        
        public Car build() {
            return this.car;
        }
    }
}
