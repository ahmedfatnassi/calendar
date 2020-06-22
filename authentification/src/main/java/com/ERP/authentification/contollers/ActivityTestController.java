package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Act;
import com.ERP.authentification.services.ActService;
import com.ERP.authentification.services.ActivitiService;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/activity")
public class ActivityTestController {
    @Autowired
    private ActivitiService activitiService ;







}
