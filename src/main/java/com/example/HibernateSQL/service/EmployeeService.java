package com.example.HibernateSQL.service;

import com.example.HibernateSQL.entity.Address;
import com.example.HibernateSQL.entity.Employee;
import com.example.HibernateSQL.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @PersistenceContext
    private EntityManager entityManager;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void demonstrateEntityStates(){
        // An entity is in the transient state when it's just created and not associated with the persistence context.
        // [TRANSIENT STATE]
        Employee newEmployee = new Employee();
        newEmployee.setName("newEmployee");

        // An entity is in the persistent state when it's associated with the persistence context and managed by the EntityManager.
        // [MANAGED / PERSISTENT STATE]
        employeeRepository.save(newEmployee);
        newEmployee.setName("managedEmployee");

        // Using flush can be helpful in scenarios where you want to ensure that the changes made to the managed entities
        // are persisted in the database before a transaction is completed, allowing you to detect any database-related
        // issues early in the transaction
        entityManager.flush();

        // An entity is in the detached state when it's no longer managed by the EntityManager.
        // clearing the persistence context
        // [DETACHED STATE]
        entityManager.clear();

        newEmployee.setName("detachedEmployee");
        // re-attached
        // as long as it is not removed it can be re-attached
        entityManager.merge(newEmployee);
        newEmployee.setName("re-attachedEmployee");

        // An entity is in the removed state when it's scheduled for removal.
        // [REMOVED STATE]
        employeeRepository.delete(newEmployee);
    }

    // annotate your custom batch insert method with @Transactional to ensure that the inserts are executed within a transaction.
    // Additionally, configure your database connection and batch insert size appropriately for optimal performance in application.properties
    // or otherwise.
    @Transactional
    public void saveEmployeesInBatch(List<Employee> employeeList){
        employeeRepository.saveAll(employeeList);
    }

}
