package com.nagarro.employeedatabase.services;

import com.nagarro.employeedatabase.models.Employee;
import com.nagarro.employeedatabase.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Method to return list of all employees
    public List<Employee> list() {
        return employeeRepository.findAll();
    }

    // Method to find an employ by employee code
    public Employee getEmployeeByEmployeeCode(Integer employeeCode) {
        return employeeRepository.getOne(employeeCode);
    }

    // Method to add an employee
    public void addEmployee(Employee employee) throws RuntimeException {

        for (Employee em : list()) {
            if (em.getEmployeeCode() == employee.getEmployeeCode()) {
                throw new RuntimeException();
            }
        }

        if (!employee.getDateOfBirth().matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new RuntimeException();
        }

        employeeRepository.saveAndFlush(employee);
    }

    // Method to delete an employee
    public void deleteEmployee(Integer employeeCode) {
        employeeRepository.deleteById(employeeCode);
    }

    // Method to update details of an employee
    public void updateEmployee(Integer employeeCode, Employee employee) throws RuntimeException {
        Employee existingEmployee = getEmployeeByEmployeeCode(employeeCode);

        if (!employee.getDateOfBirth().matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new RuntimeException();
        }

        BeanUtils.copyProperties(employee, existingEmployee);
        employeeRepository.saveAndFlush(existingEmployee);
    }

}
