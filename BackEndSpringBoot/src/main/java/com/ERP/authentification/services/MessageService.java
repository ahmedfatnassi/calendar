package com.ERP.authentification.services;

import com.ERP.authentification.Models.IndividualChatHistory;
import com.ERP.authentification.Models.Message;
import com.ERP.authentification.repositories.IndividualChatHistoryRepository;
import com.ERP.authentification.repositories.MessageRepository;
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
public class MessageService {
    @Autowired
    private com.ERP.authentification.repositories.MessageRepository messageRepository ;

    public List<Message> findAll(){
        return this.messageRepository.findAll();

    }
    public List<Message> findAllbysenderId(Long id){
        return this.messageRepository.findAllByIdsender(id);

    }
    public  Message  createPatient( Message  message ) {
        return this.messageRepository.save( message) ;

    }
    public List<Object> findTopMessagebysenderId(Long id){

        return  this.messageRepository.ouui();

    }
}
