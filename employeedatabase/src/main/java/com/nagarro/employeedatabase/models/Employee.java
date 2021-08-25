package com.nagarro.employeedatabase.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "employees")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {

    @Id
    private Integer employee_code;

    private String name;
    private String location;
    private String email;

    private String date_of_birth;

    public Employee() {}

    public Employee(Integer employeeCode, String name, String location, String email, String dateOfBirth) {
        this.employee_code = employeeCode;
        this.name = name;
        this.location = location;
        this.email = email;
        this.date_of_birth = dateOfBirth;
    }

    public Integer getEmployeeCode() {
        return employee_code;
    }

    public void setEmployeeCode(Integer employeeCode) {
        this.employee_code = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.date_of_birth = dateOfBirth;
    }
}
