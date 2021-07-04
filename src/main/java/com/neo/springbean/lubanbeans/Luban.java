package com.neo.springbean.lubanbeans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-07-04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Luban {
    String value();
}
