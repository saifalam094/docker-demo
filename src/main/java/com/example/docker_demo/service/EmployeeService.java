package com.example.docker_demo.service;

import com.example.docker_demo.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Optional<Employee> updateEmployee(Long id, Employee employeeDetails);
    boolean deleteEmployee(Long id);
}
