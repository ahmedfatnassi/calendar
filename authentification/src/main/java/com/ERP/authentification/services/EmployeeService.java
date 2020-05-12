package com.ERP.authentification.services;

import com.ERP.authentification.Models.Employee;
import com.ERP.authentification.repositories.EmployeeRepository;
import com.ERP.authentification.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll(){
        return this.employeeRepository.findAll();

    }
    public Employee create(Employee employee) {
        return this.employeeRepository.save(employee) ;

    }
    public Employee findbyid(Long id ) {
        return this.employeeRepository.findById(id).get() ;

    }
    public void deletebyid(Long id) {
                 this.employeeRepository.removeById(id); ;
         //this.personRepository.deleteById(id);

    }
}
