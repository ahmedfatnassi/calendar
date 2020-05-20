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
    public void deleteAll(){
        this.taskRepository.deleteAll();
    }

    public Task  create(Task task ) {
        return this.taskRepository.save(task) ;

    }
    public List<Task> findAllByBoardID(Long id){
        return this.taskRepository.getAllByBoardID(id);

    }
    public List<Task> updateColumn(List<Task> tasks){

      /*  // il me monque sorting
        List<Task> tasks =  this.taskRepository
                .findAllByColumnIDAndPositionBetween(idcolumn ,
                        Long.min( positioninit ,positionfinal) ,
                        Long.max( positioninit ,positionfinal) );

        for (int i = (int ) Long.min( positioninit ,positionfinal)+1; i <(int ) Long.max( positioninit ,positionfinal) ; i++) {
                if(positionfinal > positioninit){
                    tasks.get(i).setPosition(tasks.get(i).getPosition() - 1);
                }else{
                    tasks.get(i).setPosition(tasks.get(i).getPosition() + 1);
                }


        }*/

        return this.taskRepository.saveAll(tasks);
    }

    public List<Object> All(){
        Long id = new Long(169);
        return this.taskRepository.getall(id);

    }
}
