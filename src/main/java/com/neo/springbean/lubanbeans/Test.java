package com.neo.springbean.lubanbeans;

import com.neo.springbean.app.App;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Use;
import java.util.function.Supplier;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-07-02
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(App.class);
//        ac.setAllowCircularReferences(false);
//        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//        beanDefinition.setBeanClass(TestBeanFactory.class);
//        ac.registerBeanDefinition("user", beanDefinition);
//         ac.refresh();
        /**
         * register的方式
         */
//        ac.registerBean(User.class);
//        User user = ac.getBean("user", User.class);

        /**
         * Supplier的方式
         */
//        ac.registerBean(User.class, new Supplier<User>() {
//            @Override
//            public User get() {
//                User user = new User();
//                user.setName("xxxxx");
//                return user;
//            }
//        });
//        User user = ac.getBean("user", User.class);
//        System.out.println(user.getName());

//        ac.registerBean(User.class);
//
//        User user = ac.getBean("user",User.class);
//
//        System.out.println(user.getName());

        /**
         * 利用spring的beanFactory生成一个bean对象
         * BeanDefinition可以注册一个bean对象也可以注册一个beanDefinition
         */
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
////        beanFactory.registerSingleton("user",new User());
//        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//        beanDefinition.setBeanClass(User.class);
//        beanFactory.registerBeanDefinition("user",beanDefinition);
//        System.out.println(ac.getEnvironment().getPropertySources());
//        System.out.println(ac.getResource("/Users/codemao/workProjects/personal/demo/src/main/resources/applicationContext.xml"));
//
//        User user = beanFactory.getBean("user", User.class);
//        System.out.println(user);
//        UserService userService = ac.getBean("userService", UserService.class);
//        userService.test();
//        System.out.println(userService.test());
      UserInterFace userInterFace= (UserInterFace) ac.getBean("userService");
        userInterFace.test();
    }
}
