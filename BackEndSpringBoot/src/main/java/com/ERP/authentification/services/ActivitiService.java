package com.ERP.authentification.services;


import com.ERP.authentification.Models.Act;
import com.ERP.authentification.Models.BoardTask;
import com.ERP.authentification.Models.Request;
import com.ERP.authentification.repositories.TaskRepository;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ActivitiService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ManagementService managementService ;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RequestService requestService ;
    @Autowired
    private ActService actService ;
    @Autowired
    public com.ERP.authentification.services.TaskService taskBoardService ;
    public void startProcess( Long requestId) {



//DENTAL ,DENTAL_PROSTHESIS,CONSULTATION,PARAMEDICAL_MEDICAL , BIOLOGY, CHILDBIRTH , PHARMACY
        Request request = requestService.getById(requestId) ;
        List<Act> actList = actService.findAllByRequestId(requestId);
        Map<String, Object> variables = new HashMap<>(7) ;
        int[] numberacts = new int[7];
        for (int i = 0; i < actList.size(); i++) {
            if(actList.get(i).getType().equals("DENTAL")){
                numberacts[0]++;
            }else if(actList.get(i).getType().equals("DENTAL_PROSTHESIS")){
                numberacts[1]++;
            }else if(actList.get(i).getType().equals("CONSULTATION")){
                numberacts[2]++;
            }else if(actList.get(i).getType().equals("PARAMEDICAL_MEDICAL")){
                numberacts[3]++;
            }else if(actList.get(i).getType().equals("BIOLOGY")){
                numberacts[4]++;
            }else if(actList.get(i).getType().equals("CHILDBIRTH")){
                numberacts[5]++;
            }else if(actList.get(i).getType().equals("PHARMACY")){
                numberacts[6]++;
            }
        }
        variables.put("dentaleLength", numberacts[0]+"");
        variables.put("dentaleprothesisLength", numberacts[1]+"");
        variables.put("visitLength", numberacts[2]+"");
        variables.put("paramedicalLength", numberacts[3]+"");
        variables.put("biologyLength", numberacts[4]+"");
        variables.put("childbirthLength", numberacts[5]+"");
        variables.put("pharmacyLength", numberacts[6]+"");

// need to set the exclusive in case you have 0 act
        variables.put("toDoctorDental", false);
        variables.put("toDoctorProthesis",false);
        variables.put("toDoctorVisit", false);
        variables.put("toDoctorParamedical ",false );
        variables.put("toDoctorBiology",false );
        variables.put("toDoctorPharmacy ",false );
        variables.put("toDoctorChild ", false);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", variables);
        request.setActivitiProcessId(processInstance.getProcessInstanceId());
        requestService.create(request) ;
        this.createBoardTasksFromActivitiTasks(processInstance.getProcessInstanceId(),request.getDefaultColumn());
    }

    public List<Task> getAllTasksByAssignedUser(String assignee) {
        return taskService.createTaskQuery().taskCandidateOrAssigned(assignee).list();
    }
    public List<Task> getAllTasksByProcessId(String processid) {
        return  taskService.createTaskQuery().processInstanceId(processid).active().list();
    }
    public void completeTask(String taskid) {
        taskService.complete(taskid);

    }
    public void claimTask(String taskid, String user) {

        taskService.claim(taskid,user);
    }
    public void setVariableTask(String taskid, String variablename ,Object variablevalue ) {
        taskService.setVariable(taskid,variablename ,variablevalue);
    }
    public void createBoardTasksFromActivitiTasks(String processInstanceId ,  Long columnId){
        List<Task> activitiTasks = taskService.createTaskQuery().processInstanceId(processInstanceId).active().list() ;
        ArrayList<String> activitiTaskIds = new ArrayList<>() ;
        for (int i = 0; i <  activitiTasks.size(); i++) {
            activitiTaskIds.add(activitiTasks.get(i).getId()) ;
        }
        // get previous boardTask
        List<BoardTask> boardTasks = this.taskBoardService.findAllByActivitiTaskIdIn(activitiTaskIds) ;
        // know it gathered all activiti data and task Board data i need to use

        boolean[] accuredIntab = new boolean[activitiTasks.size()] ;
        for (int i = 0; i < activitiTasks.size(); i++) {
            for (int j = 0; j < boardTasks.size(); j++) {
                if(activitiTasks.get(i).equals(boardTasks.get(j))){
                    accuredIntab[i] = true ;
                }
            }
        }
        // create new tasks
        List<BoardTask> boardTasks1 = new ArrayList<>() ;
        for (int i = 0; i < activitiTasks.size(); i++) {
            if (!accuredIntab[i]){
                BoardTask task = new BoardTask() ;
                task.setColumnID(columnId) ;
               // task.setRequestId(re); ;
                task.setColor("#ffffff");
                task.setTitle(activitiTasks.get(i).getName());
                task.setActivitiTaskId(activitiTasks.get(i).getId());
                boardTasks1.add(task);

            }
        }
        //persist them
        this.taskBoardService.createAll(boardTasks1);





        // gather the activity that need to delete
        boolean[] completeTasks = new boolean[boardTasks.size()] ;
        for (int i = 0; i < boardTasks.size(); i++) {
            for (int j = 0; j < activitiTasks.size(); j++) {
                if(activitiTasks.get(j).equals(boardTasks.get(i))){
                    completeTasks[i] = true ;
                }
            }
        }
        List<BoardTask> boardTaskComplete = new ArrayList<>() ;
        for (int i = 0; i < boardTasks.size(); i++) {
            if (!completeTasks[i]){
                boardTaskComplete.add(boardTasks.get(i)) ;
            }
        }
        this.taskBoardService.deleteAll(boardTaskComplete);


        

    }
    public Task getTaskById(String taskId) {

        return  taskService.createTaskQuery().taskId(taskId).singleResult() ;
    }
}
