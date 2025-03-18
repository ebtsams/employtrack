package com.example.empTrack.repository;

import com.example.empTrack.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository  {

    private List<Employee> employees = new ArrayList<>();
    private long nextId = 1;


    public EmployeeRepository() {
        // Initialize with sample data
        employees.add(new Employee(nextId++, "Mohammad", "IT", 75000.0));
        employees.add(new Employee(nextId++, "Ahmad", "HR", 65000.0));
        employees.add(new Employee(nextId++, "Salem", "Finance", 85000.0));
        employees.add(new Employee(nextId++, "Saad", "IT", 78000.0));
    }

    public List<Employee> findAll(){
        return employees;
    }

    public Optional<Employee> findById(Long id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public List<Employee> findByDepartment(String department) {
        return employees.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    public Employee create(Employee employee){
        employee.setId(nextId++);
        employees.add(employee);
        return employee;
    }

    public Employee update(Long id,Employee employee){
        for(int i = 0;i<employees.size();i++){
            if(employees.get(i).getId().equals(id)){
                employee.setId(id); // same ID different details
                employees.set(i,employee);
                return employee;
            }
        }
        return null; // Employee not found
    }

    public void deleteById(Long id) {
        employees.removeIf(e -> e.getId().equals(id));
    }


}

