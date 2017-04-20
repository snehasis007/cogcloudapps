package com.cts.cldapps.messaging;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cts.cldapps.repositories.EmployeeRepository;


@Component
public class Receiver {
	private CountDownLatch latch = new CountDownLatch(1);
	private  EmployeeRepository employeeRepository;
	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);
	@Autowired
    public Receiver(EmployeeRepository repository) {
        this.employeeRepository = repository;
	}
	
    public void receiveMessage(String message) {
    	logger.info("Receiving Employee " + message);
              
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
