package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Medecin;
import com.ERP.authentification.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long > {
public List<Message> findAllByIdsender(Long id) ;
@Query(value ="SELECT e  FROM Message  e where (e.message_container_id= ?1)" )
public List<Message> findAllByMessagecontainerid(Long id) ;

//@Query(value ="SELECT e  FROM Message  e  where  e.send_date = (SELECT MAX(ee.send_date) FROM Message ee WHERE ee.idsender = 114)" )
@Query(value ="SELECT max (e.send_date) ,e.idsender  FROM Message  e GROUP BY (e.idsender) " )
public List<Object> ouui() ;
public List<Message> findDistinctTopByIdsender(Long id) ;
}
