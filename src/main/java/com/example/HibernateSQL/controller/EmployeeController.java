package com.example.HibernateSQL.controller;

import com.example.HibernateSQL.dto.EmployeeDetailDTO;
import com.example.HibernateSQL.entity.Employee;
import com.example.HibernateSQL.repository.EmployeeRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> fetchById(@PathVariable Integer id){
        // Optional<Employee> lazyEmployee = employeeRepository.findById(id);
        // lazy loaded by default, but eager loaded using FETCH JOIN
        Employee fetchedEmployee = employeeRepository.fetchEmployeeWithAddress(id);
        return new ResponseEntity<>(fetchedEmployee, HttpStatus.OK);
    }

    @GetMapping(value = "/projection/{id}")
    public ResponseEntity<EmployeeDetailDTO> fetchProjection(@PathVariable Integer id){
        EmployeeDetailDTO fetchedEmployee = employeeRepository.findEmployeeById(id);
        return new ResponseEntity<>(fetchedEmployee, HttpStatus.OK);
    }
}
