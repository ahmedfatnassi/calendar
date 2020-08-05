package com.ERP.authentification.services;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class CompleteProcess {
    @Autowired
    private TaskService taskService;

    public void persistAndSend (){
        System.out.println("process completed");
    }
}
