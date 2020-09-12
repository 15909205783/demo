package com.neo.spi.impl;

import com.neo.spi.api.CarInterface;

public class BlackCar implements CarInterface {
    @Override
    public void getColor() {
        System.out.println("black");
    }
}
