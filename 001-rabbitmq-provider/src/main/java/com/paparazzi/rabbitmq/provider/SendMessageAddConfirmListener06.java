package com.paparazzi.rabbitmq.provider;

import com.paparazzi.rabbitmq.util.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;

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
public class SendMessageAddConfirmListener06 {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Channel channel = RabbitMQConfig.getChannel();
        String msg = "消息异步确认测试";
        String exchangeName = "AddConfirmListenerExchange";
        String exchangeType = "direct";
        String routingKey = "AddConfirmListenerRoutingKey";
        String queueName = "AddConfirmListenerQueue";

        //声明交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true);

        //声明消息队列
        channel.queueDeclare(queueName, true, false, false, null);

        //声明绑定关系
        channel.queueBind(queueName, exchangeName, routingKey);

        //启动消息确认模式
        channel.confirmSelect();

        for (int i = 0; i <1000 ; i++) {
            //发送消息
            channel.basicPublish(exchangeName, routingKey, null, (msg+i).getBytes());
        }
        //等待消息确认
        channel.addConfirmListener(new ConfirmListener() {
            /**
             *
             * @param deliveryTag 消息id
             * @param multiple 是否批量确认
             * @throws IOException
             */
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("自动确认消息"+deliveryTag);
            }

            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("未自动确认消息"+deliveryTag);
            }
        });

        //需要确认消息触发回调函数，不能关闭资源
        //RabbitMQConfig.close();

    }


}

