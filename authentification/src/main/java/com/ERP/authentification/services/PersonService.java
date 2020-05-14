package com.ERP.authentification.services;

import com.ERP.authentification.Models.Employee;
import com.ERP.authentification.Models.Person;
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
public class PersonService {
    @Autowired
    private com.ERP.authentification.repositories.PersonRepository PersonRepository;

    public List<Person> findAll(){
        return this.PersonRepository.findAll();

    }
    public Person create(Person person) {
        return this.PersonRepository.save(person) ;

    }

    public Person findbyUsername(String username) {
        return this.PersonRepository.findByUsername(username).get() ;

    }
}
