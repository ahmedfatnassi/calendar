package com.ERP.authentification.contollers;

import java.net.http.HttpResponse;
import java.util.List;

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
@CrossOrigin(origins = "*")

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctors")
public class DoctorsController {
	@Autowired
private  DoctorServices usersService;


	@PostMapping
	public ResponseEntity<Medecin> create( @RequestBody  Medecin medecin ) {
		System.out.println("here is medecin  "+ medecin);
		return ResponseEntity.status(201).body(this.usersService.createMedecin(medecin));
	}
	@GetMapping 
	public ResponseEntity<List<Medecin>> getAll(){
		return ResponseEntity.ok().body(usersService.findAll()) ;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginForm userForm ) {
		System.out.println("iam in login");
		System.out.println(userForm.getName());
		return ResponseEntity.ok().build() ;	}

	@GetMapping("deleteAll") 
	public ResponseEntity<?> deleteAll(){
		usersService.deleteAllAll();
		return ResponseEntity.ok().build() ;
	}
	@GetMapping("{username}")
	public ResponseEntity<Medecin> getDoctorByUsername(@PathVariable String username){
		return ResponseEntity.ok().body(usersService.findbyUsername(username)) ;
	}
}
