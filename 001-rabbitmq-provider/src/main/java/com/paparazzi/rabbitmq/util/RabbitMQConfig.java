package com.paparazzi.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: RabbitMQ
 * @Package: com.paparazzi.rabbitmq.util
 * @Description: java类作用描述
 * @Author: 张仪
 * @CreateDate: 2021/2/22 19:53
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
public class RabbitMQConfig {

    private static Connection connection;

    private static Channel channel;

    public static Channel getChannel() throws IOException, TimeoutException {

        //创建连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置主机IP
        connectionFactory.setHost("192.168.18.168");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("/");
        //创建连接对象
        connection = connectionFactory.newConnection();
        //创建管道对象
        channel = connection.createChannel();
        return channel;

    }

    public static void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
