package com.paparazzi.rabbitmq.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: RabbitMQ
 * @Package: com.paparazzi.rabbitmq.consumer
 * @Description: java类作用描述
 * @Author: 张仪
 * @CreateDate: 2021/2/22 17:57
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
public class ReceiveMessage01 {

    public static void main(String[] args) throws IOException, TimeoutException {

        //创建连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置主机IP
        connectionFactory.setHost("192.168.18.168");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("/");
        //创建连接对象
        Connection connection = connectionFactory.newConnection();
        //创建管道对象
        Channel channel=connection.createChannel();
        //定义队列名称
        String queueName = "myQueue";
        //声明队列
        /*
        DeclareOk queueDeclare(String queue, 队列名称
                               boolean durable, 是否持久化
                               boolean exclusive, 是否排外
                               boolean autoDelete, 是否自动删除
                               Map<String, Object> arguments 是否有其他参数
                               ) throws IOException {}
         */
        channel.queueDeclare(queueName,true,false,false,null);

        //接收消息
        /*
        String basicConsume(String queue, 队列名称
                            boolean autoAck, 是否自动应答（true 是/false 否）
                                             自动应答：消息队列自动删除这条消息，证明我们已经成功消费了
                                             手动应答：消息队列不会自动删除这条消息，需我们手动应答，消息队列才会删除这条消息
                            Consumer callback 消费者，Consumer是一个接口，我们需要创建他的匿名内部类DefaultConsumer，实现其handleDelivery方法
                            ) throws IOException {}
         */
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("接收到消息-->"+new String(body));
            }
        });

        //不要关闭资源


    }
}
