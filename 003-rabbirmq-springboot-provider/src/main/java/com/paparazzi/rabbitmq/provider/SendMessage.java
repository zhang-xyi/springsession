package com.paparazzi.rabbitmq.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: RabbitMQ
 * @Package: com.paparazzi.rabbitmq.provider
 * @Description: java类作用描述
 * @Author: 张仪
 * @CreateDate: 2021/2/24 16:48
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
@Controller
@RequestMapping("/send")
public class SendMessage {

    @Autowired
    private AmqpTemplate amqpTemplate;


    //direct交换机模式
    @RequestMapping("/01")
    @ResponseBody
    public String sendMessage(){

        //交换机名称/路由键/消息主体
        amqpTemplate.convertAndSend("directExchange-springboot","directRoutingKey","springboot整合rabbitmq使用direct交换机模式");

        return "direct交换机模式发送消息";
    }


    //fanout是广播模式，可以进行一对多的消息发送和接受
    //消息的发送者只需要声明交换机即可
    @RequestMapping("/02")
    @ResponseBody
    //使用direct交换机来进行发送消息
    public String sendMessageFanout(){
        //void convertAndSend(String exchange, String routingKey, Object object) throws AmqpException {
        amqpTemplate.convertAndSend("fanoutExchange-springboot","","springboot整合rabbitmq使用fanout交换机模式");
        return "消息发送成功";
    }

    //topic模式
    @RequestMapping("/03")
    @ResponseBody
    //使用direct交换机来进行发送消息
    public String sendMessageTopic(){
        //void convertAndSend(String exchange, String routingKey, Object object) throws AmqpException {
        amqpTemplate.convertAndSend("topicExchange-springboot","aa","springboot整合rabbitmq使用topic交换机模式");
        amqpTemplate.convertAndSend("topicExchange-springboot","aa.bb","springboot整合rabbitmq使用topic交换机模式");
        amqpTemplate.convertAndSend("topicExchange-springboot","aa.bb.cc","springboot整合rabbitmq使用topic交换机模式");
        return "消息发送成功";
    }



}
