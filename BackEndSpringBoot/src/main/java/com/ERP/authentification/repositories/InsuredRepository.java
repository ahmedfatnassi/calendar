package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Insured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuredRepository  extends JpaRepository<Insured, Long> {
    public Insured findByUsername(String username ) ;
    public List<Insured> findAllByIdIn(List<Long> ids);
}
