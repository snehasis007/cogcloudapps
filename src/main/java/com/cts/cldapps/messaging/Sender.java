package com.cts.cldapps.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.cts.cldapps.Application;

@Component
public class Sender{
	private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    public Sender( RabbitTemplate rabbitTemplate,
            ConfigurableApplicationContext context) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setMessageConverter(new SimpleMessageConverter());
        this.context = context;
    }
    
    public void send(Object data) throws Exception {
    	logger.info("sending message"+data);
    	 rabbitTemplate.convertAndSend(Application.queueName, data);
    }
    
}
