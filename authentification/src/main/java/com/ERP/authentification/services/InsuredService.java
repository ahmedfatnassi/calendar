package com.ERP.authentification.services;

import com.ERP.authentification.Models.Act;
import com.ERP.authentification.Models.Insured;
import com.ERP.authentification.repositories.ActRepository;
import com.ERP.authentification.repositories.InsuredRepository;
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
public class InsuredService {
    @Autowired
    public InsuredRepository insuredRepository ;


    public List<Insured> findAll(){
        return this.insuredRepository.findAll();

    }
    public Insured create(Insured insured ) {
        return this.insuredRepository.save(insured) ;

    }
    public Insured findByUsername(String username  ) {
        return this.insuredRepository.findByUsername(username) ;

    }
}
