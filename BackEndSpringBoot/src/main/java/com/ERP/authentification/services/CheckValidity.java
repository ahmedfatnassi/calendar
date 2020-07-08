package com.ERP.authentification.services;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckValidity {
	 @Autowired
	    private TaskService taskService;
    public void isValide() {
    	System.out.println("check is valide... as first task ");
    	
    }

}
