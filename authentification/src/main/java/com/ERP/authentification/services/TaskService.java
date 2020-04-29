package com.ERP.authentification.services;

import com.ERP.authentification.Models.PersonBoard;
import com.ERP.authentification.Models.Task;
import com.ERP.authentification.repositories.PersonBoardRepository;
import com.ERP.authentification.repositories.TaskRepository;
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
public class TaskService {
    @Autowired
    public TaskRepository taskRepository ;


    public List<Task> findAll(){
        return this.taskRepository.findAll();

    }
    public List<Task> findAllByBoardID(Long id){
        return this.taskRepository.getAllByBoardID(id);

    }
    public Task  create(Task task ) {
        return this.taskRepository.save(task) ;

    }
}
