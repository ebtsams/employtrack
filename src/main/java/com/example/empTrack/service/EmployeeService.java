package com.example.empTrack.service;

import com.example.empTrack.exception.ResourceNotFoundException;
import com.example.empTrack.model.Employee;
import com.example.empTrack.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository)
    {
        this.employeeRepository=employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.create(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        // Check if the employee exists
        getEmployeeById(id); // This will throw ResourceNotFoundException if not found

        return employeeRepository.update(id, employeeDetails);
    }


    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.deleteById(employee.getId());
    }

}
