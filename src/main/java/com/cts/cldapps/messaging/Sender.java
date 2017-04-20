package com.cts.cldapps.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.cts.cldapps.Application;

@Component
public class Sender{
	private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final ConfigurableApplicationContext context;
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    public Sender(Receiver receiver, RabbitTemplate rabbitTemplate,
            ConfigurableApplicationContext context) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }
    
    public void send(String data) throws Exception {
    	logger.info("sending message"+data);
    	 rabbitTemplate.convertAndSend(Application.queueName, data);
    }
    
}
