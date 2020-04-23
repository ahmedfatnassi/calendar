package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Board;
import com.ERP.authentification.Models.Event;
import com.ERP.authentification.services.BoardService;
import com.ERP.authentification.services.EventServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;



    @PostMapping
    public ResponseEntity<Board> create(@RequestBody Board board) {
        return ResponseEntity.status(201).body(this.boardService.create(board));
    }

    @GetMapping
    public ResponseEntity<List<Board>> getAll()  {
        return ResponseEntity.ok().body(boardService.findAll());
    }
}
