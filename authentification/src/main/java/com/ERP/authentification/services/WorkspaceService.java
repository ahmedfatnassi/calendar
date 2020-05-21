package com.ERP.authentification.services;

import com.ERP.authentification.Models.Board;
import com.ERP.authentification.Models.Team;
import com.ERP.authentification.Models.Workspace;
import com.ERP.authentification.repositories.TeamRepository;
import com.ERP.authentification.repositories.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class WorkspaceService {
    @Autowired
    private WorkspaceRepository  workspaceRepository ;


    public List<Workspace> findAll(){
        return this.workspaceRepository.findAll();

    }
    public List<Board> findAllbyTeamId(Long id){
        return this.workspaceRepository.getboards(id) ;
        //return this.workspaceRepository.findAllByIdteam(id);

    }
    public void deleteBoardFromWorkspace(Long idteam , Long idBoard ){
        this.workspaceRepository.deleteByIdteamAndIdboard(idteam , idBoard);
    }

    public Workspace  create(Workspace workspace ) {
        return this.workspaceRepository.save(workspace) ;

    }
}
