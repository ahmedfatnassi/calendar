package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Event;
import com.ERP.authentification.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
    public List<Task>  getAllByBoardID(Long id) ;
}
