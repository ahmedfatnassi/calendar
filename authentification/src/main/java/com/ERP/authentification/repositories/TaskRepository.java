package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Event;
import com.ERP.authentification.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
    public List<Task>  getAllByBoardID(Long id) ;
    @Query("select  t  from Task t where t.columnID in (select  b.id from BoardColumn b where b.boardId = ?1) ")
    public List<Object> getall(Long Boardid) ;
}
