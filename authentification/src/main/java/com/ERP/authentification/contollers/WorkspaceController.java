package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Board;
import com.ERP.authentification.Models.Employee;
import com.ERP.authentification.Models.Team;
import com.ERP.authentification.Models.Workspace;
import com.ERP.authentification.services.SubscriptionService;
import com.ERP.authentification.services.TeamService;
import com.ERP.authentification.services.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;



    @PostMapping
    public ResponseEntity<Workspace> create(@RequestBody Workspace workspace) {
        //this.workspaceService.deleteAll();
        return ResponseEntity.status(201).body(workspaceService.create(workspace));
    }

    @GetMapping
    public ResponseEntity<List<Workspace>> getAll()
    {

        return ResponseEntity.ok().body(workspaceService.findAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<List<Board>> getallWorkspacesbyTeamid(@PathVariable Long id)
    {

        return ResponseEntity.ok().body(workspaceService.findAllbyTeamId(id));
    }
    @DeleteMapping("/delete/{idteam}/{idboard}")
    public void deleteByIteamAndIdBoard(@PathVariable Long idteam , @PathVariable Long idboard){
        this.workspaceService.deleteBoardFromWorkspace(idteam,idboard);
    }
}

