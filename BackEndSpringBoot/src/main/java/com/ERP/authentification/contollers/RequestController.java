package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Insured;
import com.ERP.authentification.Models.Request;
import com.ERP.authentification.services.InsuredService;
import com.ERP.authentification.services.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/requests")
public class RequestController {
    @Autowired
    private RequestService requestService ;

    @PostMapping
    public ResponseEntity<Request> create(@RequestBody Request request  ) {
        request.setArchived(false);
        return ResponseEntity.status(201).body(this.requestService.create(request));
    }
    @GetMapping
    public ResponseEntity<List<Request>> getNonExecutedRequestAll(){
        System.out.println("salem get all request ");
        System.out.println(requestService.findAllNonExecuted() );

        return ResponseEntity.ok().body(requestService.findAllNonExecuted() ) ;
    }

}

