package com.neo.mq.demo.confirm;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1、创建ConnectionFactory
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/vhost_mmr");
        //用户名
        factory.setUsername("user_mmr");
        //密码
        factory.setPassword("123");
        //2、获取Connection
        Connection connection = factory.newConnection();
        //3、通过Connection创建一个新的Chanel
        Channel channel = connection.createChannel();
        String exchageName = "test_confirm_exchange";
        String routingKey = "confirm.*";
        String queueName = "test_confirm_queue";
        //4、声明交换机
        channel.exchangeDeclare(exchageName, "topic", true);
        //5、声明队列
        channel.queueDeclare(queueName, true, false, false, null);
        //6、队列绑定（将队列绑定到交换机和路由key）
        channel.queueBind(queueName, exchageName, routingKey);
        //旧API
        //QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //新api
        //消息应答

        //7、定义一个消费者
        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
            //消息到达，触发这个方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                channel.basicNack(envelope.getDeliveryTag(), false,false);
                System.out.println("[1] Recv msg:" + msg);
            }
        };
        boolean autoAck = false;
        channel.basicConsume(queueName, autoAck, consumer);
    }
}
