package com.ERP.authentification.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ERP.authentification.Models.Medecin;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long > {
public  Optional<Medecin> findByUsername(String name) ;
}
