package com.cts.cldapps.messaging;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.cldapps.domain.Employee;
import com.cts.cldapps.repositories.EmployeeRepository;

@Component
public class Receiver {
	private CountDownLatch latch = new CountDownLatch(1);
	private  EmployeeRepository employeeRepository;
	@Autowired
    public Receiver(EmployeeRepository repository) {
        this.employeeRepository = repository;
	}
	
    public void receiveMessage(Object message) {
        if(message instanceof Employee){
        	Employee up=(Employee)message;
        	employeeRepository.save(up);
        }
        
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
