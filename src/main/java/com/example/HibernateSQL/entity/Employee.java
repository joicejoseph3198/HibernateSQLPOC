package com.example.HibernateSQL.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name="age_index",columnList = "age")
})
public class Employee {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Address registeredAddress;
    private int age;

}
