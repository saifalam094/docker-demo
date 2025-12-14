package com.example.docker_demo.controller;

import com.example.docker_demo.entity.Employee;
import com.example.docker_demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/ui/employees")
    public String showAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employee_list";
    }
}
