package com.cts.cldapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.cldapps.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
