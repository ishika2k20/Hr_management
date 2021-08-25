package com.nagarro.employeedatabase.repositories;

import com.nagarro.employeedatabase.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
