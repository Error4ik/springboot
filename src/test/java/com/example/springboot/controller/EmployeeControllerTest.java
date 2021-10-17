package com.example.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenRootMappingShouldReturnStatusOk() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Index Page")));
    }

    @Test
    void whenEmployeesMappingShouldReturnStatusOk() throws Exception {
        this.mockMvc.perform(get("/employees")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Employee List Page")));
    }

    @Test
    void addEmployeePage() throws Exception {
        this.mockMvc.perform(get("/addEmployee")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Add Employee")));
    }

    @Test
    void addEmployee() throws Exception {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("name", "Some Name");
        requestParams.add("surname", "Some Surname");
        requestParams.add("age", "30");
        this.mockMvc.perform(post("/addEmployee")
                .params(requestParams)).andExpect(status().is3xxRedirection());
    }
}