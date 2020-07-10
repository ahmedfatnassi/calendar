package com.ERP.authentification.services;

import com.ERP.authentification.Models.BoardColumn;
import com.ERP.authentification.repositories.ColumnRepository;
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
public class ColumnService {
    @Autowired
    public ColumnRepository columnRepository ;


    public List<BoardColumn> findAll(){
        return this.columnRepository.findAll();

    }
    public List<BoardColumn> findAllByBoardId(long id){
        return this.columnRepository.findAllByBoardId(id);

    }
    public BoardColumn findColumnById(long id){
        return this.columnRepository.findById(id).get();

    }
    public BoardColumn create(BoardColumn boardColumn) {
        return this.columnRepository.save(boardColumn) ;

    }
    public void delete(Long id ) {
         this.columnRepository.deleteById(id); ;

    }
}
