package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Task;
import com.ERP.authentification.services.TaskService;
import lombok.RequiredArgsConstructor;
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



    @PostMapping("/columnid")
    public ResponseEntity<Task> create(@RequestBody Task task,@PathVariable Long columnid) {
        return ResponseEntity.status(201).body(taskService.create(task));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll()  {
        return ResponseEntity.ok().body(taskService.findAll());
    }
}
