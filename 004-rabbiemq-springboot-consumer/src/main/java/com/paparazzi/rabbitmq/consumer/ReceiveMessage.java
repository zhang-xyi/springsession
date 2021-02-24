package com.paparazzi.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: RabbitMQ
 * @Package: com.paparazzi.rabbitmq.consumer
 * @Description: java类作用描述
 * @Author: 张仪
 * @CreateDate: 2021/2/24 17:49
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
@Component
public class ReceiveMessage {
    //该类是一个消息接收者、监听者

    //当前方法就是消息的接收者了
    //该方法的形参就是接受的消息
    //想要它变成消息的接收者必须在当前方法上添加一个注解 @RabbitListener
    @RabbitListener(queues = "directQueue-springboot")
    public void receive01Direct(String message){
        System.out.println("接受到消息:::>>>"+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(durable = "true",exclusive = "false",autoDelete = "false"),
            exchange = @Exchange(value = "fanoutExchange-springboot",type = "fanout")
    ))
    public void receive02Fanout(String message){
        System.out.println("接受到消息:::>>>"+message);
    }

    @RabbitListener(queues = "topicQueue1-springboot")
    public void receive03Topic1(String message){
        System.out.println("接受到消息:::>>>"+message);
        System.out.println("topicQueue1");
        System.out.println("-----------------------");
    }

    @RabbitListener(queues = "topicQueue2-springboot")
    public void receive03Topic2(String message){
        System.out.println("接受到消息:::>>>"+message);
        System.out.println("topicQueue2");
        System.out.println("-----------------------");
    }

    @RabbitListener(queues = "topicQueue3-springboot")
    public void receive03Topic3(String message){
        System.out.println("接受到消息:::>>>"+message);
        System.out.println("topicQueue3");
        System.out.println("-----------------------");
    }
}
