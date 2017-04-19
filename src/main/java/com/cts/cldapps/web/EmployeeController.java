package com.cts.cldapps.web;
import org.springframework.web.bind.annotation.*;

import com.cts.cldapps.domain.Employee;
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
	
	@Autowired
    public EmployeeController(EmployeeRepository repository) {
        this.employeeRepository = repository;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
    public Iterable<Employee> employees() {
        return employeeRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Employee add(@RequestBody @Valid Employee emp) {
		logger.info("Adding Employee " + emp.getId());
	    return employeeRepository.save(emp);
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public Employee update(@RequestBody @Valid Employee emp) {
        logger.info("Updating Employee " + emp.getId());
        return employeeRepository.save(emp);
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
