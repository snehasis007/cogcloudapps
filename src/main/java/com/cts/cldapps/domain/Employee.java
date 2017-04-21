package com.cts.cldapps.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	 @Column(length=40)
	 @GeneratedValue(generator="randomId")
	 @GenericGenerator(name="randomId", strategy="com.cts.cldapps.domain.RandomIdGenerator")
	 private volatile String id;
	 @JsonProperty("employeeName")
	 private volatile String employeeName;
	 @JsonProperty("employeeGrade")
	 private volatile String employeeGrade;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeGrade() {
		return employeeGrade;
	}
	public void setEmployeeGrade(String employeeGrade) {
		this.employeeGrade = employeeGrade;
	}
	 
	 
	 
	 
}
