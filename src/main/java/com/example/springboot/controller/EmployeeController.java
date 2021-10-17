package com.example.springboot.controller;

import com.example.springboot.entity.Employee;
import com.example.springboot.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


@Controller
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger();
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ModelAndView indexPage() {
        logger.info("Mapping a GET method call by url /");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("time", new Date());
        return modelAndView;
    }

    @GetMapping("/employees")
    public ModelAndView getEmployees() {
        logger.info("Mapping a GET method call by url /employees");
        ModelAndView modelAndView = new ModelAndView("employees");
        modelAndView.addObject("employeeList", this.employeeService.getEmployees());
        return modelAndView;
    }

    @GetMapping("/addEmployee")
    public ModelAndView addEmployeePage() {
        logger.info("Mapping a GET method call by url /addEmployee");
        return new ModelAndView("addEmployee");
    }

    @PostMapping("/addEmployee")
    public String addEmployee(Employee employee) {
        logger.info("Mapping a POST method call by url /addEmployee");
        this.employeeService.saveEmployee(employee);
        return "redirect:employees";
    }
}
