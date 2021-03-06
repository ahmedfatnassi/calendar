package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.BoardColumn;
import com.ERP.authentification.Models.Request;
import com.ERP.authentification.services.*;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.*;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/activiti")
public class ActivityTestController {
    @Autowired
    private ActivitiService activitiService ;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ColumnService columnService;

    @Autowired
    private ManagementService managementService ;

    @Autowired
    private HistoryService historyService;
    @Autowired
    private RequestService requestService ;
    @GetMapping("/new")
    public String  newProcess() {

       /* ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource("processes/MyProcess.bpmn")
                .deploy();*/


        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("username", "ahmed");

        variables.put("dentaleLength", "4");
        variables.put("dentaleprothesisLength", "1");
        variables.put("visitLength", "2");
        variables.put("paramedicalLength", "3");
        variables.put("biologyLength", "2");
        variables.put("childbirthLength", "1");
        variables.put("pharmacyLength", "1");
        // need to set the exclusive in case you have 0 act
        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", variables);

        return historyService
                .createHistoricTaskInstanceQuery()
                .processDefinitionKey("myProcess")
                .list().toString() ;

    }


    @GetMapping("/gettasks/{number}")
    public List<HistoricTaskInstance>  getAlltasksbyprocessID(@PathVariable String number) {
        System.out.println(number);

        List<ProcessInstance> instanceList = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionKey("myProcess")

                .list();
        //taskService.createTaskQuery().processDefinitionId("225023").list();
        Date date = new Date() ;
        System.out.println("date "+date);
        return historyService
                .createHistoricTaskInstanceQuery()
                .processDefinitionKey("myProcess")
                .processUnfinished()
                .taskVariableValueEquals("toDoctorDental",true)

                .list();
    }
    @GetMapping("/complete/{id}/{name}")
    public void  completeProcess(@PathVariable String id,@PathVariable String name) {

        taskService.setVariable(id ,name,true);
         taskService.complete(id);

    }

    @GetMapping("/execute/{requestId}/{columnId}")
    public ResponseEntity<Request>  execute(@PathVariable Long requestId,@PathVariable Long columnId) {

        Request request  = requestService.getById(requestId);
        request.setDefaultColumn(columnId);

        BoardColumn column = columnService.findColumnById(columnId) ;
        request.setBoardid(column.getBoardId());

        activitiService.startProcess(requestId) ;
        request.setArchived(true);
        requestService.create(request);

        return ResponseEntity.ok().body(request) ;
    }
}
