package com.neo.springbean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanLifecycleApplication {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean-lifecycle.xml");
        Student book = context.getBean(Student.class);
        //Bean的使用
        System.out.println("Book name = " + book.student);
        //关闭容器
        ((AbstractApplicationContext) context).close();
    }
}
