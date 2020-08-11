package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Medecin;
import com.ERP.authentification.Models.Message;
import com.ERP.authentification.Models.MessageContainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageContainerRepository   extends JpaRepository<MessageContainer, Long > {
    public List<MessageContainer> findAllByIdsenderOrIdreceiver(Long id , Long id1 ) ;
    public List<MessageContainer> findAllByIdreceiverIn(List<Long> ids ) ;

}
