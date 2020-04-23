package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumnRepository extends JpaRepository<BoardColumn, Long> {
    List<BoardColumn> findAllByBoardId(Long id) ;
}
