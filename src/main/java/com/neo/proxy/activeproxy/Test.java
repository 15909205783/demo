package com.neo.proxy.activeproxy;

import com.neo.proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        Person zhangsan = new Student("zhangsan");
        //创建一个与代理对象相关联的InvocationHandler
        InvocationHandler stuHandler = new MyInvocationHandler<Person>(zhangsan);
        //创建一个代理对象StudentProxy来代理张三，代理对象的每个执行方法都会替换成Invocation中的invoke方法
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, stuHandler);
        //代理执行上交班费的方法
        stuProxy.giveMoney(new BigDecimal(888));
    }


}
