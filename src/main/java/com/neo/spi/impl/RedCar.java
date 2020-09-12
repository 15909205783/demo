package com.neo.spi.impl;

import com.neo.spi.api.CarInterface;

public class RedCar implements CarInterface {

    @Override
    public void getColor() {
        System.out.println("Red");
    }
}
