package com.ERP.authentification.services;

import com.ERP.authentification.Models.Act;
import com.ERP.authentification.Models.Board;
import com.ERP.authentification.Models.Request;
import com.ERP.authentification.repositories.ActRepository;
import com.ERP.authentification.repositories.BoardRepository;
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
public class ActService {
    @Autowired
    public ActRepository actRepository ;


    public List<Act> findAll(){
        return this.actRepository.findAll();

    }
    public List<Act> findAllByRequestList(List<Long> requestIds){
        return this.actRepository.findAllByRequestIdIn(requestIds);

    }
    public Act  create(Act act ) {
        return this.actRepository.save(act) ;

    }
    public List<Act>  findAllByRequestId(Long id ) {
        return this.actRepository.findAllByRequestId(id) ;
    }
    public List<Act>  createAll(List<Act> acts ) {
        return this.actRepository.saveAll(acts) ;

    }
    public Act getById(Long id ) {
        return this.actRepository.findById(id).get() ;

    }
}
