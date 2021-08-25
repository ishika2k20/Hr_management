package com.nagarro.employeedatabase.controller;

import com.nagarro.employeedatabase.models.Employee;
import com.nagarro.employeedatabase.services.EmployeeService;
import com.nagarro.employeedatabase.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login/{userName}/{password}")
    public void validate(@PathVariable String userName, @PathVariable String password) {

        try {
            userService.validate(userName, password);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @GetMapping(value = "/employees")
    public List<Employee> employees() {

        List<Employee> employees = employeeService.list();
        return employees;
    }

    @GetMapping(value = "/employee/{employeeCode}")
    public Employee employee(@PathVariable Integer employeeCode) {

        Employee employee = employeeService.getEmployeeByEmployeeCode(employeeCode);
        return employee;
    }

    @GetMapping(value = "/delete/{employeeCode}")
    public List<Employee> delete(@PathVariable Integer employeeCode) {

        employeeService.deleteEmployee(employeeCode);
        List<Employee> employees = employeeService.list();

        return employees;
    }

    @GetMapping(value = "/add/{code}/{name}/{location}/{email}/{day}/{month}/{year}")
    public List<Employee> addEmployee(@PathVariable Integer code, @PathVariable String name, @PathVariable String location,
                                      @PathVariable String email, @PathVariable String day, @PathVariable String month, @PathVariable String year) throws RuntimeException {

        String date = day + "/" + month + "/" + year;
        Employee employee = new Employee(code, name, location, email, date);
        employeeService.addEmployee(employee);
        List<Employee> employees = employeeService.list();

        return employees;
    }

    @GetMapping(value = "/edit/{code}/{name}/{location}/{email}/{day}/{month}/{year}")
    public List<Employee> editEmployee(@PathVariable Integer code, @PathVariable String name, @PathVariable String location,
                                       @PathVariable String email, @PathVariable String day, @PathVariable String month, @PathVariable String year) throws RuntimeException {

        String date = day + "/" + month + "/" + year;
        Employee employee = new Employee(code, name, location, email, date);
        employeeService.updateEmployee(code, employee);
        List<Employee> employees = employeeService.list();

        return employees;
    }

}

