package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Act;
import com.ERP.authentification.Models.Insured;
import com.ERP.authentification.services.ActService;
import com.ERP.authentification.services.InsuredService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin()
@RequiredArgsConstructor
@RestController
@RequestMapping("/insureds")
public class InsuredController {
    @Autowired
    private InsuredService insuredService ;

    @PostMapping
    public ResponseEntity<Insured> create(@RequestBody Insured insured  ) {

        return ResponseEntity.status(201).body(this.insuredService.create(insured));
    }
    @GetMapping("{username}")
    public ResponseEntity<Insured> getInsuredByid(@PathVariable String username  ) {

        return ResponseEntity.status(201).body(this.insuredService.findByUsername(username));
    }
    @GetMapping
    public ResponseEntity<List<Insured>> getAll(){
        return ResponseEntity.ok().body(insuredService.findAll()) ;
    }

    @GetMapping(value = "/requestIds")
    public ResponseEntity<List<Insured>> getAllByRequestIds(@RequestParam("requestIds") List<Long> requestIds){
        System.out.println("salem get all insured  ");
        System.out.println(requestIds);
        return ResponseEntity.ok().body(insuredService.findAllInsuredByIds(requestIds)) ;
    }
}
