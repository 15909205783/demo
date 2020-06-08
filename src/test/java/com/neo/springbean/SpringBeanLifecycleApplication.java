package com.neo.springbean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanLifecycleApplication {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean-lifecycle.xml");
        Book book = (Book) context.getBean("book");
        //Bean的使用
        System.out.println("Book name = " + book.getBookName());
        //关闭容器
        ((AbstractApplicationContext) context).close();
    }
}
