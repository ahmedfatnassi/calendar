package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.IndividualChatHistory;
import com.ERP.authentification.Models.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IndividualChatHistoryRepository extends JpaRepository<IndividualChatHistory, Long > {
 public Optional<IndividualChatHistory> findALLById(long Id_receiver) ;
 public Optional<IndividualChatHistory> findByIdreceiver(long Id_receiver) ;

}
