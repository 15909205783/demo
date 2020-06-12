package com.neo.mq.demo.tx;

import com.neo.mq.demo.rabbimq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TxSend {
    private static final String QUEUE_NAME = "Ttest_queue_tx";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msgString = "hello tx message";
        try {
            channel.txSelect();
            channel.basicPublish("", QUEUE_NAME, null, msgString.getBytes());
            int xx = 1 / 0;
            System.out.println("SEND:" + msgString);
            channel.txCommit();
        } catch (Exception e) {
            System.out.println("send msg rollback");
            channel.txRollback();
        }

    }

}
