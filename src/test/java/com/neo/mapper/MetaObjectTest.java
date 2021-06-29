package com.neo.mapper;

import com.google.common.collect.Lists;
import com.neo.model.Blog;
import com.neo.model.Comment;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.junit.Before;
import org.junit.Test;
import javax.security.auth.login.AppConfigurationEntry;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-06-22
 */
public class MetaObjectTest {



    //直接操作属性
    //直接操作子属性
    //自动创建属性对象
    //自动查找属性名，支持下划线转驼峰
    //基于索引访问数组
    @Test
    public void Test(){
        //装饰器模式
        Object blog = Mock.newBlog();
//        Method getBody = blog.getClass().getDeclaredMethod("getBody");
//        Object invoke = getBody.invoke(blog);
        Configuration configuration = new Configuration();
        MetaObject metaObject = configuration.newMetaObject(blog);
        metaObject.setValue("author.email","鲁班大叔");
        metaObject.setValue("labels",new HashMap<>());
        metaObject.setValue("labels[res]","钉");
        System.out.println(metaObject.getValue("labels[res]"));
        System.out.println(metaObject.getValue("comments[0].name"));

        System.out.println(metaObject.findProperty("author.phone_number", true));

        System.out.println(metaObject.getValue("author.email"));
    }



}
