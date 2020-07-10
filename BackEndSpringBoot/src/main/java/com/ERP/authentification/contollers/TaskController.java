package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.BoardTask;
import com.ERP.authentification.Models.Person;
import com.ERP.authentification.Models.TaskResult;
import com.ERP.authentification.services.ActivitiService;
import com.ERP.authentification.services.PersonService;
import com.ERP.authentification.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ActivitiService activitiService;
    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<BoardTask> update(@RequestBody BoardTask boardTask) {

        System.out.println(boardTask);
        return ResponseEntity.status(201).body(taskService.create(boardTask));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BoardTask>> getAll(@PathVariable Long id)
    {
        System.out.println(this.taskService.All());
        return ResponseEntity.ok().body(taskService.findAllByBoardID(id));
    }
    @PostMapping("/updatecolumn")
    public ResponseEntity<List<BoardTask>> update(@RequestBody List<BoardTask> boardTasks)
    {

        return  ResponseEntity.ok().body( this.taskService.updateColumn(boardTasks));
    }
    @GetMapping("/get_task_by_columnsIds")
    public ResponseEntity<List<BoardTask>> getAll(@RequestParam("columnIds") List<Long> columnIds)
    {
        System.out.println("here salem "+columnIds);
        return ResponseEntity.ok().body(taskService.findAllByColumnIdIn(columnIds));
    }
    @PostMapping("execute")
    public ResponseEntity<BoardTask> executeTask(@RequestBody BoardTask boardTask,@RequestParam("result") TaskResult result) {
        System.out.println(boardTask);
      /*  if(!boardTask.getAssignedUser().equals(null) && !boardTask.getActivitiTaskId().equals(null) && result != null){

            Person person = personService.findbyId(boardTask.getAssignedUser());
            activitiService.claimTask(boardTask.getActivitiTaskId(),person.getUsername());*/
           Task task = activitiService.getTaskById(boardTask.getActivitiTaskId()) ;
           //
            if(task.getName().equals("validate")){
                System.out.println("validate");
                if(result.equals("APPROVED")) {
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(), "eligibility", true);
                }else {
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(), "eligibility", false);
                }
                activitiService.completeTask(boardTask.getActivitiTaskId());
            }else if(task.getName().equals("dental act")){
                System.out.println("dental act");
            }else if(task.getName().equals("dental prosthesis")){
                System.out.println("dental prosthesis");
            }else if(task.getName().equals("consultation & visits")){
                System.out.println("consultation & visits");
            }else if(task.getName().equals("paramedical&medical acts")){
                System.out.println("paramedical&medical acts");
            }else if(task.getName().equals("biology")){
                System.out.println("biology");
            }else if(task.getName().equals("pharmacy")){
                System.out.println("pharmacy");
            }else if(task.getName().equals("child birth")){
                System.out.println("child birth");
            }
            System.out.println("claimed task");
            //activitiService.createBoardTasksFromActivitiTasks();

        //}
        return ResponseEntity.status(201).body(boardTask);
    }

}
