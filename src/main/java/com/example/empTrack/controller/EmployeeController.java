package com.example.empTrack.controller;


import com.example.empTrack.exception.ResourceNotFoundException;
import com.example.empTrack.model.Employee;
import com.example.empTrack.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    // Simple Hello endpoint
    @GetMapping("/Hello")
    public String hello(){
        return "Hello!";
    }

    // Get all employees with optional department filter
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(@RequestParam(required = false) String department) {
        if (department != null && !department.isEmpty()) {
            return employeeService.getEmployeesByDepartment(department);
        }
        return employeeService.getAllEmployees();
    }

    // Get employee by Id using @PathVariable
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    // Create a new employee using @RequestBody
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    //Update an employee using @PutMapping
    @PutMapping("employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    // Delete an employee
    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    // Exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
