package com.ERP.authentification.services;

import com.ERP.authentification.Models.Board;
import com.ERP.authentification.Models.PersonBoard;
import com.ERP.authentification.repositories.BoardRepository;
import com.ERP.authentification.repositories.PersonBoardRepository;
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
public class PersonBoardService {
    @Autowired
    public PersonBoardRepository personBoardRepository ;


    public List<PersonBoard> findAll(){
        return this.personBoardRepository.findAll();

    }
    public PersonBoard  create(PersonBoard personBoard ) {
        return this.personBoardRepository.save(personBoard) ;

    }
}
