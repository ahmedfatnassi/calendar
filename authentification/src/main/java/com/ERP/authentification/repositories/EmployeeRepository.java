package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    void removeById(Long id ) ;
}
