package com.ERP.authentification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ERP.authentification.Models.Agent;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

}
