package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Act;
import com.ERP.authentification.Models.Agent;
import com.ERP.authentification.Models.Request;
import com.ERP.authentification.services.ActService;
import com.ERP.authentification.services.ActivitiService;
import com.ERP.authentification.services.AgentService;
import com.ERP.authentification.services.RequestService;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return runtimeService
                .createProcessInstanceQuery()
                .processDefinitionKey("myProcess")
                .list().toString() ;

    }


    @GetMapping("/gettasks/{number}")
    public String  getAlltasksbyprocessID(@PathVariable String number) {
        System.out.println(number);

        List<ProcessInstance> instanceList = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionKey("myProcess")
                .list();
        //taskService.createTaskQuery().processDefinitionId("225023").list();

        return taskService.createTaskQuery().processInstanceId(number).active().list().toString();

    }
    @GetMapping("/complete/{id}/{name}")
    public void  completeProcess(@PathVariable String id,@PathVariable String name) {
        taskService.claim(id, "ahmed");
        taskService.setVariable(id ,name,true);
        //taskService.setVariable(id ,"ahmed",true);
        taskService.complete(id);

    }

    @GetMapping("/execute/{requestId}/{columnId}")
    public ResponseEntity<Request>  execute(@PathVariable Long requestId,@PathVariable Long columnId) {
        Request request  = requestService.getById(requestId);
        request.setDefaultColumn(columnId);
        activitiService.startProcess(requestId) ;
        request.setArchived(true);
        requestService.create(request);

        return ResponseEntity.ok().body(request) ;
    }
}
