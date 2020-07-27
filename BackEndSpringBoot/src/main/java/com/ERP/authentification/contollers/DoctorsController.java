package com.ERP.authentification.contollers;

import java.net.http.HttpResponse;
import java.util.List;

import com.ERP.authentification.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.resource.HttpResource;

import com.ERP.authentification.Models.Medecin;
import com.ERP.authentification.Models.UserLoginForm;
import com.ERP.authentification.security.WebSecurityConfig;
import com.ERP.authentification.services.DoctorServices;
import com.ERP.authentification.services.SecurityServiceImp;

import jdk.jfr.BooleanFlag;
import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "**")

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctors")
public class DoctorsController {
	@Autowired
private  DoctorServices doctorService;


	@PostMapping
	public ResponseEntity<Medecin> create( @RequestBody  Medecin medecin ) {
		return ResponseEntity.status(201).body(this.doctorService.createMedecin(medecin));
	}
	@GetMapping 
	public ResponseEntity<List<Medecin>> getAll(){
		return ResponseEntity.ok().body(doctorService.findAll()) ;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginForm userForm ) {
		System.out.println("iam in login");
		System.out.println(userForm.getName());
		return ResponseEntity.ok().build() ;	}

	@GetMapping("deleteAll") 
	public ResponseEntity<?> deleteAll(){
		doctorService.deleteAllAll();
		return ResponseEntity.ok().build() ;
	}
	@GetMapping("{username}")
	public ResponseEntity<Medecin> getDoctorByUsername(@PathVariable String username){
		return ResponseEntity.ok().body(doctorService.findbyUsername(username)) ;
	}
	@DeleteMapping("delete/{id}")
	void deleteByID(@PathVariable Long id)
	{
		doctorService.deletebyid(id);
	}
}
