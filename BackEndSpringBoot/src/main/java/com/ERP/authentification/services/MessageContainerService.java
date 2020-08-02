package com.ERP.authentification.services;

import com.ERP.authentification.Models.Insured;
import com.ERP.authentification.Models.Message;
import com.ERP.authentification.Models.MessageContainer;
import com.ERP.authentification.repositories.InsuredRepository;
import com.ERP.authentification.repositories.MessageContainerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class MessageContainerService {
    @Autowired
    public MessageContainerRepository messageContainerRepository ;


    public List<MessageContainer> findAll(){
        return this.messageContainerRepository.findAll();

    }
    public MessageContainer create(MessageContainer messageContainer ) {
        return this.messageContainerRepository.save(messageContainer) ;

    }
    public List<MessageContainer> findAllByIdsenderOrAndIdreceiver(Long id){
        return this.messageContainerRepository.findAllByIdsenderOrIdreceiver(id,id);

    }
}
