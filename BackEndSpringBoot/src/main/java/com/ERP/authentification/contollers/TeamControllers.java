package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Employee;
import com.ERP.authentification.Models.Subscription;
import com.ERP.authentification.Models.Team;
import com.ERP.authentification.services.SubscriptionService;
import com.ERP.authentification.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teams")
public class TeamControllers {
    @Autowired
    private TeamService teamService;

    @Autowired
    private SubscriptionService subscriptionService;


    @PostMapping
    public ResponseEntity<Team> create(@RequestBody Team team) {
        return ResponseEntity.status(201).body(teamService.create(team));
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAll()
    {

        return ResponseEntity.ok().body(teamService.findAll());
    }
    @DeleteMapping("/delete/{id}")
    public void deleteTeam(@PathVariable Long id)
    {
        this.subscriptionService.deleteAllbyteamId(id) ;
        this.teamService.deleteTeam(id);

    }



}
