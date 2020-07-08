package com.ERP.authentification.services;

import com.ERP.authentification.Models.Person;
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
    private com.ERP.authentification.repositories.PersonRepository personRepository;

    public List<Person> findAll(){
        return this.personRepository.findAll();

    }
    public Person create(Person person) {
        return this.personRepository.save(person) ;

    }

    public Person findbyUsername(String username) {
        System.out.println("username");
        System.out.println(username);
        return this.personRepository.findByUsername(username).get() ;

    }
    public Person findbyId(Long id ) {

        return this.personRepository.findById(id).get() ;

    }
    public void deletebyid(Long id) {
        this.personRepository.deleteById(id) ;
        //this.personRepository.deleteById(id);

    }
}
