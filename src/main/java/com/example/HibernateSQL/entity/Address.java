package com.example.HibernateSQL.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name="city_state_index",columnList = "city,state")
})
public class Address {
    @Id
    @GeneratedValue
    private int id;
    private String city;
    private String state;
    private String addressLine;
    private String pinCode;
}
