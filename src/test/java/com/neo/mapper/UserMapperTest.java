package com.neo.mapper;

import com.neo.enums.UserSexEnum;
import com.neo.model.User;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    private Configuration configuration;

    private Connection connection;
    private JdbcTransaction jdbcTransaction;

    @Before
    public void init() throws Exception {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        configuration = sqlSessionFactory.getConfiguration();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true", "root", "root");
        jdbcTransaction = new JdbcTransaction(connection);
    }

    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new User("aa", "a123456"));
        userMapper.insert(new User("bb", "b123456"));
        userMapper.insert(new User("cc", "b123456"));

//        Assert.assertEquals(9, userMapper.getAll().size());
    }

    @Test
    public void simpleTest() throws SQLException {
        SimpleExecutor executor = new SimpleExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement("com.neo.mapper.UserMapper.selectById");
        List<Object> list = executor.doQuery(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(1));
        List<Object> list1 = executor.doQuery(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(1));

        System.out.println(list.get(0));
    }

    @Test
    public void testQuery() throws Exception {
        List<User> users = userMapper.getAll();
        if (users == null || users.size() == 0) {
            System.out.println("is null");
        } else {
            System.out.println(users.toString());
        }
    }

    @Test
    public void testQueryOne() throws Exception {
        User user = userMapper.getOne(1L);
        if (null != user) {
            System.out.println(user.getId() + "," + user.getUserName() + "," + user.getPassWord());
        } else {
            System.out.println("is null");
        }
    }


    @Test
    public void testUpdate() throws Exception {
        User user = userMapper.getOne(1L);
        System.out.println(user.toString());
        userMapper.update(user);
    }
}
