package com.paparazzi.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: RabbitMQ
 * @Package: com.paparazzi.rabbitmq.config
 * @Description: java类作用描述
 * @Author: 张仪
 * @CreateDate: 2021/2/24 16:53
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
@Configuration
public class RabbitmqConfig {

    //--------------------------------direct交换机模式start--------------------------------
    //该类用于声明Rabbitmq所需的一些参数
    //消息队列、交换机、绑定关系
    //将当前方法的返回值交给spring容器进行管理
    @Bean
    public DirectExchange directExchange(){
        //参数1：交换机名称
        //参数2：是否持久化
        //参数3：是否自动删除
        //DirectExchange(String name, boolean durable, boolean autoDelete)
        return new DirectExchange("directExchange-springboot",true,false);
    }

    //声明消息队列
    @Bean
    public Queue directQueue(){
        //参数1：队列名称
        //默认参数：持久化消息、不排外、不自动删除
        //Queue(String name)
        return new Queue("directQueue-springboot");
    }

    @Bean
    public Binding binding( Queue directQueue,DirectExchange directExchange){
        //构建者的设计模式
        //该设计模式是隐藏了创建的过程
        //最终通过调用方法的方式将Binding的返回值获取到
        //将消息队列和交换机进行绑定
        return BindingBuilder.bind(directQueue).to(directExchange).with("directRoutingKey");
    }
    /*@Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange-springboot",true,false);
    }

    @Bean
    public Queue directQueue(){
        return new Queue("directQueue-springboot");
    }

    *//**
     * 构建者设计模式
     * 隐藏了创建的过程
     * 最终通过调用方法的方式将Binding的返回值获取到
     * @return
     *//*
    @Bean
    public Binding binding(Queue directQueue,DirectExchange directExchange){
        return BindingBuilder.bind(directQueue).to(directExchange).with("directRoutingKey");
    }*/
    //--------------------------------direct交换机模式end--------------------------------

    //-------------------------------Fanout交换机模式-------------------------------
    //当前交换机为fanout模式
    //特点是可以类似广播的模式，进行消息的发送和接收
    //在消息的发送者中，只声明交换机
    //该模式下没有routingKey，只要将绑定的队列绑定到当前的交换机中，即可实现该模式
    @Bean
    public FanoutExchange fanoutExchange(){
        //参数1：交换机名称
        //参数2：是否持久化
        //参数3：是否自动删除
        //String name, boolean durable, boolean autoDelete
        return new FanoutExchange("fanoutExchange-springboot");
    }

    //-------------------------------Fanout交换机模式-------------------------------

    //-------------------------------Topic交换机模式-------------------------------

    //声明交换机
    @Bean
    public TopicExchange topicExchange(){
        //String name, boolean durable, boolean autoDelete
        return new TopicExchange("topicExchange-springboot");
    }

    //声明消息队列，3个队列
    //声明消息队列
    @Bean
    public Queue topicQueue1(){
        //参数1：队列名称
        //默认参数：持久化消息、不排外、不自动删除
        //Queue(String name)
        return new Queue("topicQueue1-springboot");
    }

    @Bean
    public Queue topicQueue2(){
        //参数1：队列名称
        //默认参数：持久化消息、不排外、不自动删除
        //Queue(String name)
        return new Queue("topicQueue2-springboot");
    }

    @Bean
    public Queue topicQueue3(){
        //参数1：队列名称
        //默认参数：持久化消息、不排外、不自动删除
        //Queue(String name)
        return new Queue("topicQueue3-springboot");
    }

    //声明绑定关系和路由规则，3个绑定关系，通配
    @Bean
    public Binding binding1( Queue topicQueue1,TopicExchange topicExchange){
        //构建者的设计模式
        //该设计模式是隐藏了创建的过程
        //最终通过调用方法的方式将Binding的返回值获取到
        //将消息队列和交换机进行绑定
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("aa");
    }

    @Bean
    public Binding binding2( Queue topicQueue2,TopicExchange topicExchange){
        //构建者的设计模式
        //该设计模式是隐藏了创建的过程
        //最终通过调用方法的方式将Binding的返回值获取到
        //将消息队列和交换机进行绑定
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("aa.*");
    }

    @Bean
    public Binding binding3( Queue topicQueue3,TopicExchange topicExchange){
        //构建者的设计模式
        //该设计模式是隐藏了创建的过程
        //最终通过调用方法的方式将Binding的返回值获取到
        //将消息队列和交换机进行绑定
        return BindingBuilder.bind(topicQueue3).to(topicExchange).with("aa.#");
    }

    //-------------------------------Topic交换机模式-------------------------------


}
