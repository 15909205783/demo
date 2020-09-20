package com.neo.zk;

import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-zookeeper.xml")
public class ZookeeperClientTest {
    @Autowired
    private ZookeeperClient zookeeperClient;

    @Test
    public void Test() {
        String patch = "/spring5/test";
        zookeeperClient.save(patch, "Spring 5 zookeeper Test", CreateMode.PERSISTENT);
        String data = zookeeperClient.query(patch);
        System.out.println("data=" + data);
    }
}
