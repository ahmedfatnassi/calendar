package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Employee;
import com.ERP.authentification.repositories.PersonRepository;
import com.ERP.authentification.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;



    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.status(201).body(employeeService.create(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll()
    {
     //   System.out.println(personRepository.findAll());
        return ResponseEntity.ok().body(employeeService.findAll());
    }

    @DeleteMapping("delete/{id}")
     void deleteByID(@PathVariable Long id)
    {
        employeeService.deletebyid(id);
    }
    @PutMapping("/employees/{id}")
    ResponseEntity<Employee> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

         Employee em = employeeService.findbyid(id) ;
         employeeService.create(em);
         return ResponseEntity.ok().body(em);

    }


}
