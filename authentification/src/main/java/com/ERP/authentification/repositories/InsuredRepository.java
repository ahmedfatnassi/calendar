package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Agent;
import com.ERP.authentification.Models.Insured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuredRepository  extends JpaRepository<Insured, Long> {
    public Insured findByUsername(String username ) ;
}
