package com.example.HibernateSQL.repository;

import com.example.HibernateSQL.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
