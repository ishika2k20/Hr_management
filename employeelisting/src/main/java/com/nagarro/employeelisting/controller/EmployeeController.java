package com.nagarro.employeelisting.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.employeelisting.models.Employee;
import com.nagarro.employeelisting.services.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    private String userName;
    private ObjectMapper mapper = new ObjectMapper();
    private File jsonFile = new File("employees.json");

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CsvService csvService;

    public void writeToFile() throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:9099/employees/",
                String.class);

        FileWriter fw = new FileWriter(jsonFile);
        fw.write(response.getBody());
        fw.close();
    }

    public List<Employee> getEmployees() {

        try {
            writeToFile();
            List<Employee> employees = mapper.readValue(jsonFile, List.class);
            return employees;
        }
        catch (Exception e) {
            List<Employee> employees = new ArrayList<>();
            return employees;
        }

    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String validate(Map<String, Object> model, @RequestParam String userName, @RequestParam String password) {

        try {
            restTemplate.getForEntity(
                            "http://localhost:9099/login/" + userName + "/" + password,
                            String.class);
        }
        catch (Exception e) {
            model.put("errorMessage", "Invalid Credentials!!");
            return "login";
        }

        List<Employee> employees = getEmployees();
        model.put("employees", employees);
        this.userName = userName;
        model.put("user", this.userName);

        return "employees";
    }

    @GetMapping(value = "/delete/{employeeCode}")
    public String delete(Map<String, Object> model, @PathVariable Integer employeeCode) {

        restTemplate.getForEntity(
                "http://localhost:9099/delete/" + employeeCode,
                String.class);

        List<Employee> employees = getEmployees();
        model.put("employees", employees);

        model.put("user", userName);
        return "employees";
    }

    @GetMapping(value = "/add")
    public String add(Map<String, Object> model) {

        model.put("link", "add");
        model.put("user", userName);
        model.put("button", "Add");
        return "employee";
    }

    @PostMapping(value = "/add")
    public String addEmployee(Map<String, Object> model, @RequestParam String code, @RequestParam String name,
                              @RequestParam String location, @RequestParam String email, @RequestParam String date) {

        try {
            restTemplate.getForEntity(
                    "http://localhost:9099/add/" + code + "/" + name + "/" +
                            location + "/" + email + "/" + date,
                    String.class);
        }
        catch (Exception e) {
            model.put("errorMessage", "Failed to Add!! Check All Fields Again");
            put(model, Integer.parseInt(code), name, location, email, date);
            model.put("link", "add");
            model.put("button", "Add");
            return "employee";
        }

        List<Employee> employees = getEmployees();
        model.put("employees", employees);
        model.put("user", userName);
        return "employees";
    }

    @GetMapping(value = "/edit/{employeeCode}")
    public String edit(@PathVariable Integer employeeCode,  Map<String, Object> model) throws IOException {

        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:9099/employee/" + employeeCode,
                String.class);

        FileWriter fw = new FileWriter(jsonFile);
        fw.write(response.getBody());
        fw.close();

        Employee employee = mapper.readValue(jsonFile, Employee.class);

        model.put("code", employeeCode);
        model.put("name", employee.getName());
        model.put("location", employee.getLocation());
        model.put("email", employee.getEmail());
        model.put("date", employee.getDateOfBirth());
        model.put("link", "edit");
        model.put("user", userName);
        model.put("readOnly", "readonly");
        model.put("button", "Save");
        return "employee";
    }

    @PostMapping(value = "/edit")
    public String editEmployee(Map<String, Object> model, @RequestParam Integer code, @RequestParam String name,
                               @RequestParam String location, @RequestParam String email, @RequestParam String date) {

        try {
            restTemplate.getForEntity(
                    "http://localhost:9099/edit/" + code + "/" + name + "/" +
                            location + "/" + email + "/" + date,
                    String.class);
        }
        catch (Exception e) {
            model.put("errorMessage", "Failed to Edit!! Check All Fields Again");
            put(model, code, name, location, email, date);
            model.put("link", "edit");
            model.put("readOnly", "readonly");
            model.put("button", "Save");
            return "employee";
        }

        List<Employee> employees = getEmployees();
        model.put("employees", employees);
        model.put("user", userName);
        return "employees";
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response) throws IOException {

        List<Employee> employees = getEmployees();
        employees = mapper.convertValue(
                employees,
                new TypeReference<List<Employee>>() { });

        csvService.exportToCSV(response, employees);

    }

    public void put(Map<String, Object> model, Integer code, String name, String location, String email, String date) {
        model.put("code", code);
        model.put("name", name);
        model.put("location", location);
        model.put("email", email);
        model.put("date", date);
        model.put("user", userName);
    }
}
