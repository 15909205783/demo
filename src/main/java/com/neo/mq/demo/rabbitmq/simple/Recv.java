package com.neo.mq.demo.rabbitmq.simple;

import com.neo.mq.demo.rabbimq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {
    private static final String QUEUE_NAME = "test_simple_queue";

    @SuppressWarnings("description")
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //创建连接
        Connection connection = ConnectionUtil.getConnection();
        //创建频道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //拿到消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("new api msg" + msg);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    private static void oldApi() throws IOException, TimeoutException, InterruptedException {
        //创建连接
        Connection connection = ConnectionUtil.getConnection();
        //创建频道
        Channel channel = connection.createChannel();
        //定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msgString = new String(delivery.getBody());
            System.out.println("[recv msg" + msgString);
        }
//        new DefaultConsumer();
    }
}
