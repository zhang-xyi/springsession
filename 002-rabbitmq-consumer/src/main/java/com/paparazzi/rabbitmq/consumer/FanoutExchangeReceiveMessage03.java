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
 * @CreateDate: 2021/2/22 20:47
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
public class FanoutExchangeReceiveMessage03 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel= RabbitMQConfig2.getChannel();

        //声明关系,生成随机队列名称
        String queueName=channel.queueDeclare().getQueue();
        System.out.println("queueName = " + queueName);

        //声明交换机
        String exchangeName="fanoutExchange";
        String exchangeType="fanout";
        channel.exchangeDeclare(exchangeName,exchangeType,true);

        //声明绑定关系
        //队列名称、交换机名称，路由键（fanout类型没有路由键，用空字符串代替）
        channel.queueBind(queueName,exchangeName,"");

        /**
         * fanout模式类似于广播，但是如果没有接收到该消息，就永远接收不到这个消息了
         */

        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("接收消息-->"+new String(body));
            }
        });

    }
}
