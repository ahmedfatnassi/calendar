package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Medecin;
import com.ERP.authentification.Models.Patient;
import com.ERP.authentification.Models.Person;
import com.ERP.authentification.services.PatientService;
import com.ERP.authentification.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService ;

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person   ) {

        return ResponseEntity.status(201).body(this.personService.create(person));
    }
    @GetMapping
    public ResponseEntity<List<Person >> getAll(){
        return ResponseEntity.ok().body(personService.findAll()) ;
    }
    @GetMapping("{username}")
    public ResponseEntity<Person> getDoctorByUsername(@PathVariable String username){
        return ResponseEntity.ok().body(personService.findbyUsername(username)) ;
    }
}
