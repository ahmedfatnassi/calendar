package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.BoardColumn;
import com.ERP.authentification.services.ColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/columns")
public class ColumnController {
    @Autowired
    private ColumnService columnService;



    @PostMapping
    public ResponseEntity<BoardColumn> create( @RequestBody BoardColumn boardColumn) {
        return ResponseEntity.status(201).body(this.columnService.create(boardColumn));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<List<BoardColumn>> getAllByBoardId(@PathVariable Long id)  {
        return ResponseEntity.ok().body(columnService.findAllByBoardId(id));
    }

}
