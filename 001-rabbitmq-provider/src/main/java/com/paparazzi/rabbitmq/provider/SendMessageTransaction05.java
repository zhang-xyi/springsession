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
public class SendMessageTransaction05 {

    public static void main(String[] args) {

        Channel channel=null;

        try {
            channel = RabbitMQConfig.getChannel();
            String msg = "transaction test";
            String exchangeName = "txExchange";
            String exchangeType = "direct";
            String routingKey = "txRoutingKey";
            String queueName = "txQueue";

            //声明交换机
            channel.exchangeDeclare(exchangeName, exchangeType, true);

            //声明消息队列
            channel.queueDeclare(queueName,true,false,false,null);

            //声明绑定关系
            channel.queueBind(queueName, exchangeName, routingKey);

            //开启事务
            channel.txSelect();

            //发送消息
            channel.basicPublish(exchangeName,routingKey,null,msg.getBytes());

            //提交事务
            channel.txCommit();
        } catch (Exception e) {
            e.printStackTrace();
            //异常回滚
            try {
                channel.txRollback();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            //关闭资源
            try {
                RabbitMQConfig.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }


    }
}
