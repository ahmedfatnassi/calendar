package com.ERP.authentification.services;


import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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


    public void startProcess(Map<String, Object> variables) {
        runtimeService.startProcessInstanceByKey("hireProcessWithJpa", variables);
    }

    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().list();
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
}
