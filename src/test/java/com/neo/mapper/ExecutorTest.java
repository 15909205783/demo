package com.neo.mapper;

import org.apache.ibatis.executor.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecutorTest {
    private Configuration configuration;
    private Connection connection;
    private JdbcTransaction jdbcTransaction;
    private SqlSessionFactory sqlSessionBuilder;

    @Before
    public void init() throws SQLException, IOException {
        String resource = "mybatis/mybatis-config.xml";
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionBuilder = factoryBuilder.build(inputStream);
        configuration = sqlSessionBuilder.getConfiguration();
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true", "root", "root");
        jdbcTransaction = new JdbcTransaction(connection);
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
    public void reuseTest() throws SQLException {
        ReuseExecutor executor = new ReuseExecutor(configuration, jdbcTransaction);

        MappedStatement ms = configuration.getMappedStatement("com.neo.mapper.UserMapper.selectById");
        List<Object> list = executor.doQuery(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(1));
        List<Object> list1 = executor.doQuery(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(1));
        System.out.println(list.get(0));
    }

    @Test
    public void batchTest() throws SQLException {
        BatchExecutor executor = new BatchExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement("com.neo.mapper.UserMapper.setName");
        Map param = new HashMap();
        param.put("arg0", 1);
        param.put("arg1", "鲁班大叔");
        executor.doUpdate(ms, param);
        executor.doUpdate(ms, param);
        executor.doUpdate(ms, param);
    }

    @Test
    public void BaseExecutorTest() throws SQLException {
        Executor executor = new SimpleExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement("com.neo.mapper.UserMapper.selectById");
        executor.query(ms, 1, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        executor.query(ms, 1, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
    }

    @Test
    public void cacheExecutor() throws SQLException {
        Executor executor = new SimpleExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement("com.neo.mapper.UserMapper.selectById");
        Executor cachingExecutor = new CachingExecutor(executor);
        cachingExecutor.query(ms, 1, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        cachingExecutor.commit(true);//1.先走二级缓存 2.再走一级缓存
        cachingExecutor.query(ms, 1, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
    }

    @Test
    public void sessionTest() {
        SqlSession sqlSession = sqlSessionBuilder.openSession(true);
        //降低调用的复杂性
        //com.neo.mapper.UserMapper.selectById这个其实就是为在configuration中获取MappedStatement，再调用executor.query
        List<Object> list = sqlSession.selectList("com.neo.mapper.UserMapper.selectById", 1);
        System.out.println(list.get(0));

    }
}
