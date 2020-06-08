package com.neo.springbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class Book implements BeanNameAware,BeanFactoryAware,ApplicationContextAware,InitializingBean, DisposableBean {

    private String bookName;

    public Book() {
        System.out.println("Book Initializing ");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Book.destory invoke");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Book.setBeanFactory invoke");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Book.setBeanName invoke");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Book.afterPropertiesSet invoke");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Book.setApplicationContext invoke");
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public void myPostConstruct(){
        System.out.println("Book.myPostConstruct invoke");
    }

    // 自定义初始化方法

    public void springPostConstruct(){
        System.out.println("@PostConstruct");
    }

    public void myPreDestory(){
        System.out.println("Book.myPreDestory invoke");
        System.out.println("---------------destroy-----------------");
    }

    // 自定义销毁方法

    public void springPreDestory(){
        System.out.println("@PreDestory");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("------inside finalize-----");
    }
}
