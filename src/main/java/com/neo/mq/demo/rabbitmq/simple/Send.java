package com.neo.mq.demo.rabbitmq.simple;

import com.neo.mq.demo.rabbimq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中获取一个通道
        Channel channel = connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        String msg = "hello simple ！";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println("msg:" + msg);
        channel.close();
        connection.close();
    }
}
