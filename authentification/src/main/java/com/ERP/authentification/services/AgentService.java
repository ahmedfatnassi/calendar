package com.ERP.authentification.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ERP.authentification.Models.Agent;
import com.ERP.authentification.Models.Medecin;
import com.ERP.authentification.repositories.AgentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor 
@Slf4j
@Service
@Transactional
public class AgentService {
	@Autowired
	private AgentRepository agentRepository ; 
	
	public List<Agent> findAll(){
		return this.agentRepository.findAll();
				
	}
	public Agent createAgent(Agent agent ) {
		return this.agentRepository.save(agent) ;
		
	}

}
