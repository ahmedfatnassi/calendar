package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Act;
import com.ERP.authentification.Models.BoardTask;
import com.ERP.authentification.Models.Person;
import com.ERP.authentification.Models.TaskResult;
import com.ERP.authentification.services.ActService;
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
    private ActService actService ;
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
    public ResponseEntity<BoardTask> executeTask(@RequestBody BoardTask boardTask,@RequestParam("result") TaskResult result,@RequestParam("report") String report) {
        System.out.println(boardTask);
      /*  if(!boardTask.getAssignedUser().equals(null) && !boardTask.getActivitiTaskId().equals(null) && result != null){

            Person person = personService.findbyId(boardTask.getAssignedUser());
            activitiService.claimTask(boardTask.getActivitiTaskId(),person.getUsername());*/
           Task task = activitiService.getTaskById(boardTask.getActivitiTaskId()) ;
        System.out.println("report");
        System.out.println(report);
        Act act = actService.getById(boardTask.getActId()) ;
        act.setResult(result.toString());
        act.setResult_description(report);
        actService.create(act);
        System.out.println(act);
           /* if(task.getName().equals("validate")){
                System.out.println("validate");
                if(result.equals("APPROVED")) {
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(), "eligibility", true);
                }else {
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(), "eligibility", false);
                }
                activitiService.completeTask(boardTask.getActivitiTaskId());

            }else */

           if(task.getName().equals("dental act")){
                System.out.println("dental act");
                if(result.toString().equals("APPROVED")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorDental",false );
                }else if(result.toString().equals("DISAPPROVED")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorDental",false );
                } else if(result.toString().equals("TODOCTOR")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorDental",true );
                }
            }else if(task.getName().equals("dental prosthesis")){
                System.out.println("dental prosthesis");
            if(result.toString().equals("APPROVED")){
                activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorProthesis",false );

                }else if(result.toString().equals("DISAPPROVED")){
                activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorProthesis",false );
                } else if(result.toString().equals("TODOCTOR")){
                activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorProthesis",true );
                }
            }else if(task.getName().equals("consultation & visits")){
                System.out.println("consultation & visits");

                if(result.toString().equals("APPROVED")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorVisit",false );
                }else if(result.toString().equals("DISAPPROVED")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorVisit",false );
                } else if(result.toString().equals("TODOCTOR")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorVisit",true );
                }
            }else if(task.getName().equals("paramedical&medical acts")){
                System.out.println("paramedical&medical acts");
                if(result.toString().equals("APPROVED")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorParamedical",false );
                }else if(result.toString().equals("DISAPPROVED")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorParamedical",false );
                } else if(result.toString().equals("TODOCTOR")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorParamedical",true );
                }
            }else if(task.getName().equals("biology")){
                System.out.println("biology");
                if(result.toString().equals("APPROVED")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorBiology",false );
                }else if(result.toString().equals("DISAPPROVED")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorBiology",false );
                } else if(result.toString().equals("TODOCTOR")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorBiology",true );
                }
            }else if(task.getName().equals("pharmacy")){
                System.out.println("pharmacy");
                if(result.toString().equals("APPROVED")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorPharmacy",false );
                }else if(result.toString().equals("DISAPPROVED")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorPharmacy",false );
                } else if(result.toString().equals("TODOCTOR")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorPharmacy",true );
                }
            }else if(task.getName().equals("child birth")){
                System.out.println("child birth");
                if(result.toString().equals("APPROVED")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorChild",false );
                }else if(result.toString().equals("DISAPPROVED")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorChild",false );
                } else if(result.toString().equals("TODOCTOR")){
                    activitiService.setVariableTask(boardTask.getActivitiTaskId(),"toDoctorChild",true );
                }
            }


            System.out.println("claimed task");
            //activitiService.createBoardTasksFromActivitiTasks();
        activitiService.claimTask(boardTask.getActivitiTaskId(), boardTask.getAssignedUser().toString());
        //activitiService.completeTask(boardTask.getActivitiTaskId());
        activitiService.completeApprovedTask(boardTask);
        //}
        return ResponseEntity.status(201).body(boardTask);
    }

}
