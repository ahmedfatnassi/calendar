package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Employee;
import com.ERP.authentification.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    void removeById(Long id ) ;

}
