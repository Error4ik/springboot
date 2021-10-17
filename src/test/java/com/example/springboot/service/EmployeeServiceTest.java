package com.example.springboot.service;

import com.example.springboot.entity.Employee;
import com.example.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private final EmployeeRepository repository = mock(EmployeeRepository.class);
    private final EmployeeService employeeService = new EmployeeService(repository);
    private final static Employee employee = new Employee("name", "surname", 30);

    @BeforeAll
    static void init() {
        employee.setId(10L);
    }

    @Test
    void whenSaveEmployeeShouldReturnSavedEmployee() {
        when(repository.save(any(Employee.class))).thenReturn(employee);

        Employee actualEmployee = employeeService.saveEmployee(employee);

        assertEquals(employee, actualEmployee);
        verify(repository, times(1)).save(employee);
    }

    @Test
    void whenGetEmployeeByIdShouldReturnEmployee() {
        when(repository.getById(any(Long.class))).thenReturn(employee);

        Employee actualEmployee = employeeService.getEmployeeById(employee.getId());

        assertEquals(employee, actualEmployee);
        verify(repository, times(1)).getById(employee.getId());
    }

    @Test
    void whenGetEmployeesShouldReturnEmployeeList() {
        when(repository.findAll()).thenReturn(Arrays.asList(employee));

        List<Employee> employees = employeeService.getEmployees();

        assertEquals(employee, employees.get(0));
        verify(repository, times(1)).findAll();
    }

    @Test
    void removeEmployee() {
        employeeService.removeEmployee(employee);

        verify(repository, times(1)).delete(employee);
    }
}