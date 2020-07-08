package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PersonRepository  extends JpaRepository<Person,Long> {
    public Optional<Person> findByUsername(String username);
}
