package com.paparazzi.rabbitmq.consumer;

import com.paparazzi.rabbitmq.util.RabbitMQConfig2;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: RabbitMQ
 * @Package: com.paparazzi.rabbitmq.consumer
 * @Description: java类作用描述
 * @Author: 张仪
 * @CreateDate: 2021/2/23 20:20
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
public class ReceiveMessageAddConfirmListener06 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQConfig2.getChannel();
        String queueName = "AddConfirmListenerQueue";
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("成功接收消息-->" + new String(body));
            }
        });
    }
}
