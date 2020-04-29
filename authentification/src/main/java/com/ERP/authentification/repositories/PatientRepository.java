package com.ERP.authentification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ERP.authentification.Models.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

}
