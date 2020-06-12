package com.neo.mq.demo.confirm;

import com.neo.mq.demo.rabbimq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

public class Send3 {
    private static final String QUEUE_NAME = "Ttest_queue_confirm3";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //生产者调用confirmSelect将channel设置为confirm模式
        channel.confirmSelect();
        //存放的是未确认的消息
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
       //通道添加ack
        channel.addConfirmListener(new ConfirmListener() {
            //没有问题的
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("---------handleAck-----------multiple");
                    System.out.println(deliveryTag);
                    confirmSet.headSet(deliveryTag = +1).clear();
                } else {
                    System.out.println("---------handleAck-----------multiple");
                    confirmSet.remove(deliveryTag);
                }
            }

            //有问题的
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("---------handleAck-----------multiple");

                    confirmSet.headSet(deliveryTag = +1).clear();
                } else {
                    System.out.println("---------handleAck-----------multiple");
                    confirmSet.remove(deliveryTag);
                }
            }
        });
        String msgStr = "sssssssss";
        while (true) {
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("", QUEUE_NAME, null, msgStr.getBytes());
            confirmSet.add(seqNo);
        }
    }
}
