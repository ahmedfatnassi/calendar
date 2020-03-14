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
import com.ERP.authentification.Models.Medecin;
import com.ERP.authentification.repositories.AgentRepository;
import com.ERP.authentification.services.AgentService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/agents")
public class AgentControllers {
	@Autowired
	private AgentService agentService ;
	
	@PostMapping
	public ResponseEntity<Agent> create( @RequestBody  Agent agent  ) {

		return ResponseEntity.status(201).body(this.agentService.createAgent(agent));
	}
	@GetMapping 
	public ResponseEntity<List<Agent>> getAll(){
		return ResponseEntity.ok().body(agentService.findAll()) ;
	}
	
}
