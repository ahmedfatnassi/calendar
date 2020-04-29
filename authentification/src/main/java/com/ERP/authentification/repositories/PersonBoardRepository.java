package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Event;
import com.ERP.authentification.Models.PersonBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonBoardRepository extends JpaRepository<PersonBoard, Long> {
}
