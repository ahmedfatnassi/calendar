package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Employee;
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
        return ResponseEntity.ok().body(employeeService.findAll());
    }

    @DeleteMapping("delete/{id}")
     void deleteByID(@PathVariable Long id)
    {
        System.out.println("delete "+id);
        employeeService.deletebyid(id);
    }
    /*@PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }*/


}
