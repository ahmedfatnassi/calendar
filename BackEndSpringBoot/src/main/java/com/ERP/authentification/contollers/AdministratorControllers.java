package com.ERP.authentification.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ERP.authentification.Models.Administrator;
import com.ERP.authentification.services.AdministratorService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/administrators")
public class AdministratorControllers {
	@Autowired
	private AdministratorService administratorService ;
	
	@PostMapping
	public ResponseEntity<Administrator> create(@RequestBody Administrator administrator) {

		return ResponseEntity.status(201).body(this.administratorService.createAdministrator(administrator));
	}
	@GetMapping 
	public ResponseEntity<List<Administrator>> getAll(){
		return ResponseEntity.ok().body(administratorService.findAll()) ;
	}
	@DeleteMapping("delete/{id}")
	void deleteByID(@PathVariable Long id)
	{
		administratorService.deletebyid(id);
	}
}
