package com.neo.springbean.services;

import lombok.Data;
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
public class Y {
    X x;

    public Y() {
        System.out.println("Y created");
    }
}
