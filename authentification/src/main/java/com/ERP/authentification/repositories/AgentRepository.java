package com.ERP.authentification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ERP.authentification.Models.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {

}
