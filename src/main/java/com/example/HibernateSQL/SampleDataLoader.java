package com.example.HibernateSQL;

import com.example.HibernateSQL.entity.Address;
import com.example.HibernateSQL.entity.Employee;
import com.example.HibernateSQL.repository.EmployeeRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;
    private final Faker faker;

    public SampleDataLoader(Faker faker, EmployeeRepository employeeRepository){
        this.faker = faker;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sample data present: "+!employeeRepository.findAll().isEmpty());
        if(employeeRepository.findAll().isEmpty()){
            List<Employee> employeeList = IntStream.rangeClosed(1,100)
                    .mapToObj(i-> new Employee(
                            0,
                            faker.name().fullName(),
                            new Address(
                                    0,
                                    faker.address().city(),
                                    faker.address().state(),
                                    faker.address().fullAddress(),
                                    faker.address().zipCode()
                            ),
                            faker.number().numberBetween(18,70)
                    )).toList();
            employeeRepository.saveAll(employeeList);
        }
    }
}
