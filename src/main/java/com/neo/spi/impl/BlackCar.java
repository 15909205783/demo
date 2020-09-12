package com.neo.spi.impl;

import com.neo.spi.api.CarInterface;

public class BlackCar implements CarInterface {
    @Override
    public void hello() {
        System.out.println("black");
    }
}
