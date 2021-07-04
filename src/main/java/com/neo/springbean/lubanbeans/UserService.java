package com.neo.springbean.lubanbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-07-04
 */
@Component
public class UserService {

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
}
