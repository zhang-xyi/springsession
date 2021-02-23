package com.paparazzi.rabbitmq.provider;

import com.paparazzi.rabbitmq.util.RabbitMQConfig;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: RabbitMQ
 * @Package: com.paparazzi.rabbitmq.provider
 * @Description: java类作用描述
 * @Author: 张仪
 * @CreateDate: 2021/2/22 20:01
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
public class DirectExchangeSendMessage02 {
    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel= RabbitMQConfig.getChannel();
        String msg="directExchange send message";
        String exchangeName="directExchange";
        String exchangeType="direct";
        //声明交换机
        /*
        DeclareOk exchangeDeclare(String exchange, 交换机名称
                                  String type, 交换机类型
                                  boolean durable 是否持久化
                                  ) throws IOException {}
         */
        channel.exchangeDeclare(exchangeName,exchangeType,true);

        //声明消息队列
        String queueName="directQueue";
        /*
        DeclareOk queueDeclare(String queue,
                               boolean durable,
                               boolean exclusive,
                               boolean autoDelete,
                               Map<String, Object> arguments
                               ) throws IOException {}
         */
        channel.queueDeclare(queueName,true,false,false,null);

        //声明绑定关系
        /*
        BindOk queueBind(String queue, 消息队列名称
                         String exchange, 交换机名称
                         String routingKey 路由键
                         ) throws IOException {}
         */
        String routingKey="directRoutingKey";
        channel.queueBind(queueName,exchangeName,routingKey);

        //发送消息到指定队列
        channel.basicPublish(exchangeName,routingKey,null,msg.getBytes());

        System.out.println("发送消息成功");

        //关闭资源
        RabbitMQConfig.close();

    }
}
