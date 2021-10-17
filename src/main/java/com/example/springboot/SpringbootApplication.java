package com.example.springboot;

import com.example.springboot.entity.Employee;
import com.example.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class SpringbootApplication {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @PostConstruct
    private void fillingInTable() {
        List<Employee> employees = employeeService.getEmployees();
        if (employees.size() == 0) {
            for (int i = 0; i < 10; i++) {
                employeeService.saveEmployee(new Employee("Name " + i, "Surname " + i, 25 + i));
            }
        }
    }
}
