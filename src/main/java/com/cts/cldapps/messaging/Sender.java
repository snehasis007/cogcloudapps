package com.cts.cldapps.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

import com.cts.cldapps.Application;

@Component
public class Sender{
	private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final ConfigurableApplicationContext context;

    public Sender(Receiver receiver, RabbitTemplate rabbitTemplate,
            ConfigurableApplicationContext context) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }
    
    public void send(Object data) throws Exception {
    	 rabbitTemplate.convertAndSend(Application.queueName, data);
    	 //receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    	 //context.close();
    }
    
    /*@Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(Application.queueName, "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        context.close();
    }*/

}
