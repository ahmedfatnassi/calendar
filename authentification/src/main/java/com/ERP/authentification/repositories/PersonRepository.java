package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    public Optional<Person> findByUsername(String username);
}