package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.BoardTask;
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



    @PostMapping
    public ResponseEntity<BoardTask> create(@RequestBody BoardTask boardTask) {
        return ResponseEntity.status(201).body(taskService.create(boardTask));
    }
    @PostMapping
    public ResponseEntity<BoardTask> update(@RequestBody BoardTask boardTask) {
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

}
