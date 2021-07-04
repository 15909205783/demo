package com.neo.springbean.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-06-30
 */
@Component
public class X {
    Y y;

    public X() {
        System.out.println("X creat");
    }
}
