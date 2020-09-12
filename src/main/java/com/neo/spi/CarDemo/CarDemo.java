package com.neo.spi.CarDemo;


import com.neo.spi.api.CarInterface;

import java.util.ServiceLoader;

public class CarDemo {
    public static void main(String[] agrs) {
        ServiceLoader<CarInterface> loaders = ServiceLoader.load(CarInterface.class);
        for (CarInterface carInterface : loaders) {
            carInterface.hello();
        }
    }
}
