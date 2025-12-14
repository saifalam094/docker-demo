package com.example.docker_demo.service;

import com.example.docker_demo.entity.Employee;
import com.example.docker_demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> updateEmployee(Long id, Employee employeeDetails) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(employeeDetails.getName());
            employee.setRole(employeeDetails.getRole());
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setCity(employeeDetails.getCity());
            employee.setDescription(employeeDetails.getDescription());
            employee.setPhoneNumber(employeeDetails.getPhoneNumber());
            return employeeRepository.save(employee);
        });
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
