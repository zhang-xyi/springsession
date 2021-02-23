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
 * @CreateDate: 2021/2/22 21:04
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
public class TopicExchangeReceiveMessage04 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel= RabbitMQConfig2.getChannel();

        //声明消息队列
        String queueName1="topicQueue1";
        String queueName2="topicQueue2";
        String queueName3="topicQueue3";


        channel.basicConsume(queueName3,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("接收到消息-->"+new String(body));
            }
        });

    }
}
