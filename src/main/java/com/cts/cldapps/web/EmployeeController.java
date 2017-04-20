package com.cts.cldapps.web;
import org.springframework.web.bind.annotation.*;

import com.cts.cldapps.domain.Employee;
import com.cts.cldapps.domain.Message;
import com.cts.cldapps.messaging.Sender;
import com.cts.cldapps.repositories.EmployeeRepository;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	private  EmployeeRepository employeeRepository;
	private Sender sender; 
	
	@Autowired
    public EmployeeController(EmployeeRepository repository,Sender sender) {
        this.employeeRepository = repository;
        this.sender=sender;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
    public Iterable<Employee> employees() {
        return employeeRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Message add(@RequestBody @Valid Employee emp) {
		logger.info("Adding Employee " + emp.getId());
		Message ms=new Message();
		try {
			logger.info("sending Employee : " + emp.getId());
			sender.send(emp.toString());
			employeeRepository.save(emp);
			logger.info("sent : " + emp.getId());
		} catch (Exception e) {
			ms.setMessage("not Added");
			return ms;
		}
		ms.setMessage("added");
	    return ms;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public Message update(@RequestBody @Valid Employee emp) {
        logger.info("Updating Employee " + emp.getId());
        Message ms=new Message();
		try {
			sender.send(emp.toString());
			employeeRepository.save(emp);
		} catch (Exception e) {
			ms.setMessage("not Added");
			return ms;
		}
		ms.setMessage("added");
	    return ms;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getById(@PathVariable String id) {
        logger.info("Getting Employee " + id);
        return employeeRepository.findOne(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable String id) {
        logger.info("Deleting Employee " + id);
        employeeRepository.delete(id);
	}
}
