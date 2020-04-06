package com.ERP.authentification.contollers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERP.authentification.AuthentificationApplication;
import com.ERP.authentification.Models.Event;
import com.ERP.authentification.services.EventServices;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {
	@Autowired
	private EventServices eventservice;
    
    

	@PostMapping
	public ResponseEntity<Event> create(@RequestBody Event event) {
		return ResponseEntity.status(201).body(this.eventservice.create(event));
	}

	@GetMapping
	public ResponseEntity<List<Event>> getAll()  {
		return ResponseEntity.ok().body(eventservice.findAll());
	}

	@GetMapping("/non_archived")
	public ResponseEntity<List<Event>> getAllNonArchived() {
		return ResponseEntity.ok().body(eventservice.findAllNonArchived());
	}

	@GetMapping("/set_archived/{id}")
	public ResponseEntity<Void> setArchived(@PathVariable Long id) {
		return ResponseEntity.status(eventservice.setArchived(id)).build();
	}

	@GetMapping("/doctor_event/{id}")
	public ResponseEntity<List<Event>> doctor_event(@PathVariable Long id) {
		return ResponseEntity.status(200).body(eventservice.findAllbyReceiverId(id));
	}

	@GetMapping("/deleteAll")
	public void deleteAll() {
		eventservice.deleteALl();
	}

}
