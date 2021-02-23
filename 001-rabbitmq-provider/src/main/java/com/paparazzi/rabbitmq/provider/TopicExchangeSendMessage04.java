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
 * @CreateDate: 2021/2/22 20:59
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
public class TopicExchangeSendMessage04 {

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel= RabbitMQConfig.getChannel();
        String exchangeName="topicExchange";
        String exchangeType="topic";
        //声明交换机,一个交换机
        /*
        DeclareOk exchangeDeclare(String exchange, 交换机名称
                                  String type, 交换机类型
                                  boolean durable 是否持久化
                                  ) throws IOException {}
         */
        channel.exchangeDeclare(exchangeName,exchangeType,true);

        //声明消息队列
        String queueName1="topicQueue1";
        String queueName2="topicQueue2";
        String queueName3="topicQueue3";
        /*
        DeclareOk queueDeclare(String queue,
                               boolean durable,
                               boolean exclusive,
                               boolean autoDelete,
                               Map<String, Object> arguments
                               ) throws IOException {}
         */
        channel.queueDeclare(queueName1,true,false,false,null);
        channel.queueDeclare(queueName2,true,false,false,null);
        channel.queueDeclare(queueName3,true,false,false,null);

        //声明绑定关系
        /*
        BindOk queueBind(String queue, 消息队列名称
                         String exchange, 交换机名称
                         String routingKey 路由键
                         ) throws IOException {}
         */
        String routingKey1="aa";
        String routingKey2="aa.*";
        String routingKey3="aa.#";
        channel.queueBind(queueName1,exchangeName,routingKey1);
        channel.queueBind(queueName2,exchangeName,routingKey2);
        channel.queueBind(queueName3,exchangeName,routingKey3);


        String msg1="msg 1";
        String msg2="msg 22";
        String msg3="msg 333";

        String msgRoutingKey1="aa";
        String msgRoutingKey2="aa.bb";
        String msgRoutingKey3="aa.bb.bb";

        //发送消息到指定队列
        channel.basicPublish(exchangeName,msgRoutingKey3,null,msg3.getBytes());

        System.out.println("发送消息成功");

        //关闭资源
        RabbitMQConfig.close();
    }
}
