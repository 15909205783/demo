package com.neo.springbean.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-06-30
 */
@Component
public class B implements ApplicationContextAware, InitializingBean {

    @Autowired
    A a;

    @PostConstruct
    public void initMethod() {
        System.out.println("life callback anno");
    }

    public B() {
        System.out.println("B was created!");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("initializingBean call back");
    }
}
