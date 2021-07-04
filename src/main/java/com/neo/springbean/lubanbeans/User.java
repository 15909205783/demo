package com.neo.springbean.lubanbeans;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-07-02
 */
@Component
public class User {
    private String name;
    private String email;
    private String pwd;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @PostConstruct
    private void initName(){
        this.name = "杨帆";
    }
}
