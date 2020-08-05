package com.ERP.authentification.services;

import com.ERP.authentification.Models.Insured;
import com.ERP.authentification.Models.Request;
import com.ERP.authentification.repositories.InsuredRepository;
import com.ERP.authentification.repositories.RequestRepository;
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
public class RequestService {
    @Autowired
    public RequestRepository requestRepository ;


    public List<Request> findAll(){
        return this.requestRepository.findAll();

    }
    public List<Request> saveAll(List<Request> l){
        return this.requestRepository.saveAll(l);

    }
    public List<Request> findAllNonExecuted(){
        return this.requestRepository.findAllByIsArchivedIsFalse();

    }
    public List<Request> findAllFinished(){
        return this.requestRepository.findAllByIsExecutedIsTrue();

    }

    public Request create(Request request ) {
        return this.requestRepository.save(request) ;

    }
    public Request getById(Long id ) {
        return this.requestRepository.findById(id).get() ;

    }
}
