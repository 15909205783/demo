package com.neo.mq.demo.confirm;

import com.neo.mq.demo.rabbimq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send2 {
    private static final String QUEUE_NAME = "Ttest_queue_confirm1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //生产者调用confirmSelect将channel设置为confirm模式
        channel.confirmSelect();
        //批量，发完之后再确认
        for (int i = 0; i < 10; i++) {
            String msgString = "hello confirm messge" + i;
            channel.basicPublish("", QUEUE_NAME, null, msgString.getBytes());

        }
        if (!channel.waitForConfirms()) {
            System.out.println("message send fail");
        } else {
            System.out.println("message send ok");
        }
        channel.close();
        connection.close();
    }
}
