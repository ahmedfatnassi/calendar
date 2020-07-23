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
    private  TaskRepository taskRepository;

    @Autowired
    public com.ERP.authentification.services.TaskService taskBoardService ;
    public void startProcess( Long requestId) {



//DENTAL ,DENTAL_PROSTHESIS,CONSULTATION,PARAMEDICAL_MEDICAL , BIOLOGY, CHILDBIRTH , PHARMACY
        Request request = requestService.getById(requestId) ;
        List<Act> actList = actService.findAllByRequestId(requestId);
        System.out.println(actList);
        Map<String, Object> variables = new HashMap<>(7) ;
        int[] numberacts = new int[7];
        variables.put("toDoctorDental", false);
        variables.put("toDoctorProthesis",false);
        variables.put("toDoctorVisit", false);
        variables.put("toDoctorParamedical",false );
        variables.put("toDoctorBiology",false );
        variables.put("toDoctorPharmacy",false );
        variables.put("senderEmailAddress","ahmedfatnassi23@gmail.com" );
        variables.put("toDoctorChild", false);
        for (int i = 0; i < actList.size(); i++) {
            if(actList.get(i).getType().toString().equals("DENTAL")){
                numberacts[0]++;
            }else if(actList.get(i).getType().toString().equals("DENTAL_PROSTHESIS")){
                numberacts[1]++;
            }else if(actList.get(i).getType().toString().equals("CONSULTATION")){
                numberacts[2]++;
            }else if(actList.get(i).getType().toString().equals("PARAMEDICAL_MEDICAL")){
                numberacts[3]++;
            }else if(actList.get(i).getType().toString().equals("BIOLOGY")){
                numberacts[4]++;
            }else if(actList.get(i).getType().toString().equals("CHILDBIRTH")){
                numberacts[5]++;
            }else if(actList.get(i).getType().toString().equals("PHARMACY")){
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

        variables.put("eligibility", true);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", variables);
        request.setActivitiProcessId(processInstance.getProcessInstanceId());
        requestService.create(request) ;
        this.createAllTaskFromActs(request.getDefaultColumn(),processInstance.getProcessInstanceId(),actList) ;
        //this.orchestration(processInstance.getProcessInstanceId(),request.getDefaultColumn());
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
    public void orchestration(String processInstanceId ,  Long columnId){
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
    public void createAllTaskFromActs(Long columnId ,String processInstanceId , List<Act> acts ) {
        List<Task> activitiTasks = taskService.createTaskQuery().processInstanceId(processInstanceId).active().list() ;
        List<BoardTask> boardTasks = new ArrayList<>() ;
        boolean[] added = new boolean[acts.size()] ;
        for (int i = 0; i < activitiTasks.size(); i++) {

            BoardTask taskBoard = new BoardTask() ;
            taskBoard.setColumnID(columnId) ;
            taskBoard.setColor("#ffffff");
            taskBoard.setTitle(activitiTasks.get(i).getName());
            taskBoard.setActivitiTaskId(activitiTasks.get(i).getId());

// it only
                if(activitiTasks.get(i).getName().equals("dental act")){
                    for (int j = 0; j <acts.size(); j++) {
                        if(acts.get(j).getType().toString().equals("DENTAL") && !added[j]){
                            taskBoard.setActId(acts.get(j).getId());
                            added[j]= true;
                            break;
                        }
                    }
                }else if(activitiTasks.get(i).getName().equals("dental prosthesis")){
                    System.out.println("dental prosthesis");
                    for (int j = 0; j <acts.size(); j++) {
                        if(acts.get(j).getType().toString().equals("DENTAL_PROSTHESIS") && !added[j]){
                            taskBoard.setActId(acts.get(j).getId());
                            added[j]= true;
                            break;
                        }
                    }
                }else if(activitiTasks.get(i).getName().equals("consultation & visits")){
                    System.out.println("consultation & visits");
                    for (int j = 0; j <acts.size(); j++) {
                        if(acts.get(j).getType().toString().equals("CONSULTATION") && !added[j]){
                            taskBoard.setActId(acts.get(j).getId());
                            added[j]= true;
                            break;
                        }
                    }
                }else if(activitiTasks.get(i).getName().equals("paramedical&medical acts")){
                    System.out.println("paramedical&medical acts");
                    for (int j = 0; j <acts.size(); j++) {
                        if(acts.get(j).getType().toString().equals("PARAMEDICAL_MEDICAL") && !added[j]){
                            taskBoard.setActId(acts.get(j).getId());
                            added[j]= true;
                            break;
                        }
                    }
                }else if(activitiTasks.get(i).getName().equals("biology")){
                    System.out.println("biology");
                    for (int j = 0; j <acts.size(); j++) {
                        if(acts.get(j).getType().toString().equals("BIOLOGY") && !added[j]){
                            taskBoard.setActId(acts.get(j).getId());
                            added[j]= true;
                            break;
                        }
                    }
                }else if(activitiTasks.get(i).getName().equals("pharmacy")){
                    System.out.println("pharmacy");
                    for (int j = 0; j <acts.size(); j++) {
                        if(acts.get(j).getType().toString().equals("PHARMACY") && !added[j]){
                            taskBoard.setActId(acts.get(j).getId());
                            added[j]= true;
                            break;
                        }
                    }
                }else if(activitiTasks.get(i).getName().equals("child birth")){
                    System.out.println("child birth");
                    for (int j = 0; j <acts.size(); j++) {
                        if(acts.get(j).getType().toString().equals("CHILDBIRTH") && !added[j]){
                            taskBoard.setActId(acts.get(j).getId());
                            added[j]= true;
                            break;
                        }
                    }
                }
            System.out.println("taskBoard");
            System.out.println(taskBoard);
            boardTasks.add(taskBoard);

        }
        List<BoardTask> list = this.taskBoardService.createAll(boardTasks);
        System.out.println(list);
    }
    public BoardTask completeApprovedTask(BoardTask task) {

        taskService.complete(task.getActivitiTaskId());

        Request request = requestService.getById(actService.getById(task.getActId()).getRequestId());

        List<Act> acts  = actService.findAllByRequestId(actService.getById(task.getActId()).getRequestId()) ;
        task.setColor("#ffffff");
        task.setAssignedUser(null);
        task.setColumnID(request.getDefaultColumn());
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(request.getActivitiProcessId()).active().list();
        List<Long> actIds = new ArrayList<>();
        for (int i = 0; i < acts.size(); i++) {
            actIds.add(acts.get(i).getId());
        }
        List<BoardTask> boardTasks = taskBoardService.findAllByActIdIn(actIds) ;
        boolean in ;
        int activitiNewTaskindex =-1 ;

        System.out.println(boardTasks.toString());
        System.out.println(tasks.toString());
        if(boardTasks.size() == tasks.size()) {
            for (int i = 0; i < tasks.size(); i++) {
                in = false;
                for (int j = 0; j < boardTasks.size(); j++) {
                    if (tasks.get(i).getId() == boardTasks.get(j).getActivitiTaskId()) {
                        in = true;
                        break;
                    }
                }
                if (!in) {
                    activitiNewTaskindex = i;
                    System.out.println("activitiNewTaskindex ");
                    break;
                }
            }

                task.setActivitiTaskId(tasks.get(activitiNewTaskindex).getId());
                task.setTitle("Doctor Task" );
                taskBoardService.create(task) ;
                System.out.println("task completed and create new task");
                 return task ;

        } else {
            System.out.println("task completed and deleted ");
            taskBoardService.delete(task.getId());
            return null ;
        }


    }
    public void ToDoctorComplete (Long taskid) {

    }

}
