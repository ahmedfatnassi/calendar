package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Medecin;
import com.ERP.authentification.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long > {
public List<Message> findAllByIdsender(Long id) ;
}
