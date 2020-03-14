package com.ERP.authentification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ERP.authentification.Models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	public List<Event> findAllByArchivedFalse();
	public List<Event> findByIdReceiver(Long idReceiver);
}
