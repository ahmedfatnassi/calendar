package com.ERP.authentification.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ERP.authentification.Models.Medecin;
import com.ERP.authentification.Models.Patient;
import com.ERP.authentification.repositories.PatientRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional 
public class PatientService {
	@Autowired
	private PatientRepository patientRepository ;

	public List<Patient> findAll(){
		return this.patientRepository.findAll();
				
	}
	public Patient  createPatient(Patient patient ) {
		return this.patientRepository.save(patient) ;
		
	}
}
