package com.ERP.authentification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ERP.authentification.Models.Administrator;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}
