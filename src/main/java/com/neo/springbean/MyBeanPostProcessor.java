package com.neo.springbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    //对初始化之前的Bean进行处理
    //参数：bean：即初始化的bean
    //beanname：bean的名称
    //返回值：返回给用户的那个bean，可以修改bean返回一个新bean
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("对初始化之前的Bean进行处理,此时我的名字" + bean);
        if (bean instanceof Book) {
            System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization");
        }
        return bean;
    }

    //对初始化之后的bean进行处理
    //参数：bean：即将初始化的bean
    //参数：beanname：bean的名称
    //返回值：返回给用户那个bean，可以修改bean也可以返回一个新bean
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Student stu = null;
        Book book = null;
        System.out.println("对初始化之后的Bean进行处理,将Bean的成员变量的值修改了");
        if ("name".equals(beanName) || bean instanceof Student) {
            stu = (Student) bean;
            stu.setName("Jack");
            return stu;
        }
        if (bean instanceof Book) {
            System.out.println("MyBeanPostProcessor.postProcessAfterInitialization");
            book = (Book) bean;
            book.setBookName("红楼梦");
            return bean;
        }
        return bean;
    }
}
