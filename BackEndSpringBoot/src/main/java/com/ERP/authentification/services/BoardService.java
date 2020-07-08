package com.ERP.authentification.services;

import com.ERP.authentification.Models.Board;
import com.ERP.authentification.Models.Patient;
import com.ERP.authentification.repositories.BoardRepository;
import com.ERP.authentification.repositories.MedecinRepository;
import com.ERP.authentification.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class BoardService {
    @Autowired
    public BoardRepository boardRepository ;


    public List<Board> findAll(){
        return this.boardRepository.findAll();

    }
    public Board  create(Board board ) {
        return this.boardRepository.save(board) ;

    }
}
