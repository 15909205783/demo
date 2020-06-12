package com.neo.mq.demo.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        //虚拟host
        connectionFactory.setVirtualHost("/vhost_mmr");
        connectionFactory.setUsername("user_mmr");
        //密码
        connectionFactory.setPassword("123");
        //2、获取Connection
        Connection connection = connectionFactory.newConnection();
        //3、通过Connection创建一个新的Chanel
        Channel channel = connection.createChannel();
        //4、指定我们的消息投递模式：消息确认模式
        channel.confirmSelect();
        String exchageName = "test_confirm_exchange";
        String routingKey = "confirm.save";
        //5、发送一条消息
        String msg = "Hello RbbitMQ send confirm message!";
        channel.basicPublish(exchageName, routingKey, null, msg.getBytes());
        //6、回调ack机制:消费者收到消息后会返回一个回调确认消息
//        if(channel.waitForConfirms()) {
//            System.out.println("send success!");
//        } else{
//            System.out.println("send error!");
//        }
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("-------------no ack------------");
            }
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("------------------- ack---------");
            }

        });
    }
}
