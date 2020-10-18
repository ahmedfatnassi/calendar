package com.ERP.authentification.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ERP.authentification.Models.Administrator;
import com.ERP.authentification.repositories.AdministratorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor 
@Slf4j
@Service
@Transactional
public class AdministratorService {
	@Autowired
	private AdministratorRepository administratorRepository;
	
	public List<Administrator> findAll(){
		return this.administratorRepository.findAll();
				
	}
	public Administrator createAdministrator(Administrator administrator) {
		return this.administratorRepository.save(administrator) ;
	}
	public void deletebyid(Long id) {
		this.administratorRepository.deleteById(id); ;
		//this.personRepository.deleteById(id);

	}
}
