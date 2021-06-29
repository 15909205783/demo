package com.neo.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-06-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParamTest {

    private UserMapper userMapper;

    private Configuration configuration;

    private Connection connection;

    private JdbcTransaction jdbcTransaction;

    private SqlSession sqlSession;


    @Before
    public void init() throws Exception {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlsessionBuilder = new SqlSessionFactoryBuilder().build(inputStream);
        configuration = sqlsessionBuilder.getConfiguration();
        sqlSession = sqlsessionBuilder.openSession();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true", "root", "root");
        jdbcTransaction = new JdbcTransaction(connection);
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void over() {
        sqlSession.close();
    }

    @Test
    public void singleTest() {
        userMapper.selectByIdOrName(1,"aa");
    }

    @Test
    public void test(){
        List<Object> list = new ArrayList<>();
        ResultHandler handler = new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
                if (resultContext.getResultCount()==1){
                    resultContext.stop();
                }
                list.add(resultContext.getResultObject());
            }
        };
        sqlSession.select("com.neo.mapper.UserMapper.selectById2",handler);
        System.out.println(list.size());
    }
}
