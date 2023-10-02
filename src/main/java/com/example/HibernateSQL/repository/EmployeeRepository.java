package com.example.HibernateSQL.repository;

import com.example.HibernateSQL.entity.Employee;
import com.example.HibernateSQL.dto.EmployeeDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    // Even when using fetch joins, JPA still supports lazy loading for other associations that are not included in the fetch join.
    // JOIN FETCH in a query fetches related entities eagerly for that specific query,
    // but it does not change the default fetching behavior specified in the entity mapping.
    @Query("SELECT e from Employee e JOIN FETCH e.registeredAddress where e.id = :employeeId")
    Employee fetchEmployeeWithAddress(@Param("employeeId") Integer employeeId);

    // Record Projection, similarly you can use interface projection if ( < Java 16) and constructor projection
    // use aliases (AS) to map columns to the properties of the nested records.

    // Unfortunately, you can’t rely on Spring Data JPA’s automatic mapping feature when using a native query.
    @Query(
            value = "SELECT" +
                    " e.id AS id, " +
                    " e.name AS name, " +
                    " a.city AS registeredAddress_city, " +
                    " a.pinCode AS registeredAddress_pinCode " +
                    "FROM Employee e " +
                    "LEFT JOIN e.registeredAddress a "+
                    "WHERE e.id = :employeeId "
    )
    EmployeeDetailDTO findEmployeeById(@Param("employeeId") Integer employeeId);

}
