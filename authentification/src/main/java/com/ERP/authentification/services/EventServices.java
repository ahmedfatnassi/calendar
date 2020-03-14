package com.ERP.authentification.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ERP.authentification.Models.Event;
import com.ERP.authentification.repositories.EventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor 
@Slf4j
@Service
@Transactional
public class EventServices {
@Autowired	
public  EventRepository eventRepository ;

public Event create(Event event ) {
	return this.eventRepository.save(event) ;
}
public List<Event> findAll(){
	return this.eventRepository.findAll();
}
public List<Event> findAllNonArchived(){
	return this.eventRepository.findAllByArchivedFalse();
}
public int setArchived(Long id) {
	Optional<Event> event = this.eventRepository.findById(id) ; 
	if(event.isEmpty()) {
		return 404 ;
	}else {
		event.orElseThrow().setArchived(true);
		return 202 ;
	}
	
}
public void deleteALl() {
	this.eventRepository.deleteAll();
}
public List<Event> findAllbyReceiverId(Long id){
	return this.eventRepository.findByIdReceiver(id) ;
}
}
