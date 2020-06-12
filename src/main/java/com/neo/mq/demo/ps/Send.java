package com.neo.mq.demo.ps;

import com.neo.mq.demo.rabbimq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String EXCHANGE_NAME = "test_exchange_famout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//分发
        //发送消息
        String msg = "hello ps";
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        System.out.println("send:" + msg);
        channel.close();
        connection.close();
    }

}
