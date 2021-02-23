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
 * @CreateDate: 2021/2/23 20:04
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
public class SendMessageWaitForConfirmsOrDie06 {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Channel channel = RabbitMQConfig.getChannel();
        String msg = "批量消息确认";
        String exchangeName = "WaitForConfirmsOrDieExchange";
        String exchangeType = "direct";
        String routingKey = "WaitForConfirmsOrDieRoutingKey";
        String queueName = "WaitForConfirmsOrDieQueue";

        //声明交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true);

        //声明消息队列
        channel.queueDeclare(queueName, true, false, false, null);

        //声明绑定关系
        channel.queueBind(queueName, exchangeName, routingKey);

        //启动消息确认模式
        channel.confirmSelect();

        for (int i = 0; i <100 ; i++) {
            //发送消息
            channel.basicPublish(exchangeName, routingKey, null, (msg+i).getBytes());
            //等待消息确认
            channel.waitForConfirms();
            System.out.println(msg+i+"发送成功");
        }

        RabbitMQConfig.close();

    }


}

