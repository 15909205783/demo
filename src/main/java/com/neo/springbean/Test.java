package com.neo.springbean;

import com.neo.springbean.app.App;
import com.neo.springbean.lubanbeans.User;
import com.neo.springbean.services.X;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * Description:Spring生命周期
 * </p>
 *
 * @author yangwuhai
 * @since 2021-06-30
 */
public class Test {
    public static void main(String[] args) {
        /**
         * bean是怎样被实例化的
         *
         * 1、scan---bd--map
         * 2、遍历map
         * 3、validate
         * 4、得到class
         * 5、推断构造方法
         * 6、反射 实例化这个对象
         * 7、合并beanDefinition
         * 8、提前暴露一个bean工厂
         * 9、填充属性----自动注入
         * 10、部分aware接口
         * 11、执行---部分aware接口  执行spring生命周期回调方法
         * 12、接口版的生命周期回调方法
         * 13、beanPostProcessor的前置方法-----aop
         * 14、put到单例池
         */
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(App.class);
//        ac.setAllowCircularReferences(false);
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        ac.registerBeanDefinition("user", beanDefinition);
       // ac.refresh();
        User user = ac.getBean("user", User.class);
        System.out.println(user);
        System.out.println(ac.getBean(X.class));
        System.out.println(user.getName());
    }
}
