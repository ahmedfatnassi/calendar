package com.ERP.authentification.services;

import com.ERP.authentification.Models.BoardTask;
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


    public List<BoardTask> findAll(){
        return this.taskRepository.findAll();

    }
    public void deleteAll(){
        this.taskRepository.deleteAll();
    }

    public BoardTask create(BoardTask boardTask) {
        return this.taskRepository.save(boardTask) ;

    }
    public BoardTask update(BoardTask boardTask) {
        return this.taskRepository.save(boardTask) ;

    }
    public List<BoardTask> findAllByBoardID(Long id){
        return this.taskRepository.getAllByBoardID(id);

    }
    public List<BoardTask> findAllByActIdIn(List<Long> ids){
        return this.taskRepository.findAllByActIdIn(ids);

    }
    public List<BoardTask> findAllByActivitiTaskIdIn (List<String> activiTaskIds){
        return this.taskRepository.findAllByActivitiTaskIdIn(activiTaskIds);

    }
    public List<BoardTask> findAllByColumnIdIn (List<Long> columnsId){
        return this.taskRepository.findAllByColumnIDIn(columnsId);

    }
    public List<BoardTask> updateColumn(List<BoardTask> boardTasks){

      /*  // il me monque sorting
        List<BoardTask> boardTasks =  this.taskRepository
                .findAllByColumnIDAndPositionBetween(idcolumn ,
                        Long.min( positioninit ,positionfinal) ,
                        Long.max( positioninit ,positionfinal) );

        for (int i = (int ) Long.min( positioninit ,positionfinal)+1; i <(int ) Long.max( positioninit ,positionfinal) ; i++) {
                if(positionfinal > positioninit){
                    boardTasks.get(i).setPosition(boardTasks.get(i).getPosition() - 1);
                }else{
                    boardTasks.get(i).setPosition(boardTasks.get(i).getPosition() + 1);
                }


        }*/

        return this.taskRepository.saveAll(boardTasks);
    }

    public List<Object> All(){
        Long id = new Long(169);
        return this.taskRepository.getall(id);

    }
    public List<BoardTask> createAll( List<BoardTask> list){
        return this.taskRepository.saveAll(list);
    }

    public void deleteAll( List<BoardTask> list){

         this.taskRepository.deleteAll(list);

    }
    public void delete( Long id ){

        this.taskRepository.deleteById(id);

    }
}
