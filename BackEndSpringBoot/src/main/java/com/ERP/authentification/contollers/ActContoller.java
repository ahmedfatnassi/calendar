package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Act;
import com.ERP.authentification.services.ActService;
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
    public ResponseEntity<List<Act>> createall(@RequestBody List<Act> acts  ) {

        return ResponseEntity.status(201).body(this.actService.createAll(acts));
    }

    @GetMapping
    public ResponseEntity<List<Act>> getAll(){
        return ResponseEntity.ok().body(actService.findAll()) ;
    }

    @GetMapping(value = "/requestIds")
    public ResponseEntity<List<Act>> getAllByRequestIds(@RequestParam("requestIds") List<Long> requestIds){
        System.out.println("salem get all Acts ");
        System.out.println(requestIds);
        return ResponseEntity.ok().body(actService.findAllByRequestList(requestIds)) ;
    }
    @GetMapping("/{id}")
    ResponseEntity<Act> get(@PathVariable Long id) {


        return ResponseEntity.ok().body(actService.getById(id));

    }
}
