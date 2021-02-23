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
 * @CreateDate: 2021/2/22 20:35
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
public class FanoutExchangeSendMessage03 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQConfig.getChannel();
        String msg="fanoutExchangeSendMessage";
        String exchangeName="fanoutExchange";
        String exchangeType="fanout";

        //声明交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true);

        //声明消息队列
        String queueName="fanoutQueue";
        channel.queueDeclare(queueName,true,false,false,null);

        //声明绑定关系
        //队列名称、交换机名称，路由键（fanout类型没有路由键，用空字符串代替）
        channel.queueBind(queueName,exchangeName,"");

        //发送消息到指定消息队列
        channel.basicPublish(exchangeName,"",null,msg.getBytes());
        System.out.println("消息发送成功");
        //关闭资源
        RabbitMQConfig.close();

    }
}
