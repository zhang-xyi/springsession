package com.paparazzi.rabbitmq.provider;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: RabbitMQ
 * @Package: com.paparazzi.rabbitmq.provider
 * @Description: java类作用描述
 * @Author: 张仪
 * @CreateDate: 2021/2/22 17:31
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
public class SendMessage01 {

    public static void main(String[] args) throws IOException, TimeoutException {

        //创建连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置主机ip
        connectionFactory.setHost("192.168.18.168");
        //设置端口
        connectionFactory.setPort(5672);
        //设置用户名
        connectionFactory.setUsername("root");
        //设置密码
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("/");
        //创建连接对象
        Connection connection = connectionFactory.newConnection();
        //创建管道对象
        Channel channel = connection.createChannel();

        String msg = "hello rabbitmq";
        //创建消息队列
        /*
         DeclareOk queueDeclare(String provider, 消息队列名称
                                boolean durable, 是否持久化
                                boolean exclusive, 是否排外（消息只能由一人接收）
                                boolean autoDelete, 是否自动删除
                                Map<String, Object> arguments 其他参数
                                ) throws IOException {}
         */
        channel.queueDeclare("myQueue", true, false, false, null);

        //发送消息到指定队列
        /*
        void basicPublish(String exchange, 交换机名称
                          String routingKey, 路由键
                          BasicProperties props, 属性
                          byte[] body 消息的字节数组
                          ) throws IOException {}
         */
        channel.basicPublish("", "myQueue", null, msg.getBytes());
        System.out.println("消息发送成功");
        channel.close();
        connection.close();


    }
}
