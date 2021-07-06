package com.neo.mapper;

import com.neo.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * 一级缓存命中场景
 */
public class FirstCacheTest {
    private Configuration configuration;
    private Connection connection;
    private JdbcTransaction jdbcTransaction;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;

    @Before
    public void init() throws SQLException, IOException {
        String resource = "mybatis/mybatis-config.xml";
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream(resource);
        factory = factoryBuilder.build(inputStream);
        configuration = factory.getConfiguration();
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true", "root", "root");
        jdbcTransaction = new JdbcTransaction(connection);
        sqlSession = factory.openSession();
    }

    /**
     * 一级缓存命中的条件
     * 1、sql和参数必须相同
     * 2、必须是相同的statementID
     * 3、sqlSession必须一样（会话级缓存）
     * 4.RowBounds返回行必须相同
     */
    @Test
    public void test() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectById(1);
        RowBounds rowBounds = RowBounds.DEFAULT;
        List<Object> list = sqlSession.selectList("com.neo.mapper.UserMapper.selectById",1, rowBounds);
        System.out.println(user==list.get(0));
    }

    @Test
    public void test2() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectById(1);
        RowBounds rowBounds = RowBounds.DEFAULT;
//        sqlSession.clearCache();
        List<Object> list = sqlSession.selectList("com.neo.mapper.UserMapper.selectById",1, rowBounds);
        System.out.println(user==list.get(0));
    }
}
