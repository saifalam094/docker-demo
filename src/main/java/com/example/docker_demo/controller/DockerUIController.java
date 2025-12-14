package com.example.docker_demo.controller;

import com.example.docker_demo.entity.Employee;
import com.example.docker_demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/ui")
public class DockerUIController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/create")
    public String showCreateForm() {
        return "employee_form";
    }

    @PostMapping("/create")
    public String handleCreateEmployee(@RequestParam String name, @RequestParam String role, @RequestParam(required = false) String phoneNumber, @RequestParam(required = false) String description, LocalDateTime createdDate,Model model) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setRole(role);
        employee.setPhoneNumber(phoneNumber);
        employee.setDescription(description);
        employee.setCreatedDate(createdDate);
        Employee savedEmployee = employeeService.createEmployee(employee);
        model.addAttribute("employee", savedEmployee);
        return "employee_status";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id).orElse(null);
        if (employee == null) {
            return "redirect:/ui/employees";
        }
        model.addAttribute("employee", employee);
        return "employee_edit_form";
    }

    @PostMapping("/edit/{id}")
    public String handleEditEmployee(@PathVariable Long id,
                                     @RequestParam String name,
                                     @RequestParam String role,
                                     @RequestParam String phoneNumber,
                                     @RequestParam String description,
                                     Model model) {
        Employee updated = new Employee();
        updated.setName(name);
        updated.setRole(role);
        updated.setPhoneNumber(phoneNumber);
        updated.setDescription(description);
        employeeService.updateEmployee(id, updated);
        return "redirect:/ui/employees";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/ui/employees";
    }

}
