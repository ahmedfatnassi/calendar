package com.ERP.authentification.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERP.authentification.Models.Agent;
import com.ERP.authentification.Models.Patient;
import com.ERP.authentification.repositories.PatientRepository;
import com.ERP.authentification.services.AgentService;
import com.ERP.authentification.services.PatientService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/patients")
public class PatientControllers {
	@Autowired
	private PatientService patientService ;
	
	@PostMapping
	public ResponseEntity<Patient> create( @RequestBody  Patient patient   ) {

		return ResponseEntity.status(201).body(this.patientService.createPatient(patient));
	}
	@GetMapping 
	public ResponseEntity<List<Patient >> getAll(){
		return ResponseEntity.ok().body(patientService.findAll()) ;
	}
}
