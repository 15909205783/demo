package com.neo.springbean.lubanbeans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-07-02
 */
@Component("user")
public class TestBeanFactory implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        Person person = new Person();
        return person;
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }
}
