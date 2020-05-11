package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Subscription;
import com.ERP.authentification.Models.Task;
import com.ERP.authentification.services.SubscriptionService;
import com.ERP.authentification.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;



    @PostMapping
    public ResponseEntity<Subscription> create(@RequestBody Subscription subscription) {
        return ResponseEntity.status(201).body(subscriptionService.create(subscription));
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAll()
    {
        return ResponseEntity.ok().body(subscriptionService.findAll());
    }

}
