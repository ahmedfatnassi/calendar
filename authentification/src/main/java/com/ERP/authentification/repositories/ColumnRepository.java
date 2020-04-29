package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ColumnRepository extends JpaRepository<BoardColumn, Long> {
    List<BoardColumn> findAllByBoardId(Long id) ;
}
