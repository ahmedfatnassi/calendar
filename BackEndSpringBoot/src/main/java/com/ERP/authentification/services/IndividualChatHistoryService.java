package com.ERP.authentification.services;

import com.ERP.authentification.Models.IndividualChatHistory;
import com.ERP.authentification.Models.Patient;
import com.ERP.authentification.repositories.IndividualChatHistoryRepository;
import com.ERP.authentification.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class IndividualChatHistoryService {
    @Autowired
    private IndividualChatHistoryRepository individualChatHistoryRepository ;

    public List<IndividualChatHistory> findAll(){
        return this.individualChatHistoryRepository.findAll();

    }
    public Optional<IndividualChatHistory> findAllById(Long id ){
        return this.individualChatHistoryRepository.findById(id);

    }
    public Optional<IndividualChatHistory> findByPerson(Long id ){
        return this.individualChatHistoryRepository.findByIdreceiver(id);

    }
    public IndividualChatHistory  create(IndividualChatHistory individualChatHistory ) {
        return this.individualChatHistoryRepository.save(individualChatHistory) ;

    }
}
