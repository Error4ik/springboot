package com.example.springboot.service;

import com.example.springboot.entity.Employee;
import com.example.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        return this.employeeRepository.getById(employeeId);
    }

    public List<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }

    public void removeEmployee(Employee employee) {
        this.employeeRepository.delete(employee);
    }
}
