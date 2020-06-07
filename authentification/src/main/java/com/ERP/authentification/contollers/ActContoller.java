package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Act;
import com.ERP.authentification.Models.Agent;
import com.ERP.authentification.services.ActService;
import com.ERP.authentification.services.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/acts")
public class ActContoller {
    @Autowired
    private ActService actService ;

    @PostMapping
    public ResponseEntity<Act> create(@RequestBody Act act  ) {

        return ResponseEntity.status(201).body(this.actService.create(act));
    }
    @GetMapping
    public ResponseEntity<List<Act>> getAll(){
        return ResponseEntity.ok().body(actService.findAll()) ;
    }

}
