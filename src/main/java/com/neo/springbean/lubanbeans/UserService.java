package com.neo.springbean.lubanbeans;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-07-04
 */
@Component
public class UserService implements InitializingBean, UserInterFace {

    private User user;

    //    @Luban("ccccccc")
//    private String name;
//
//    public void test() {
//        System.out.println(name);
//    }
    public UserService() {
        System.out.println("构造方法");
    }

    public User getUser() {
        return user;
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
        System.out.println("set注入");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化");
    }

    @PostConstruct
    public void xxx(){
        System.out.println("xxxxxx");
    }

    @Override
    public void test() {
        System.out.println("业务逻辑");
    }
}
