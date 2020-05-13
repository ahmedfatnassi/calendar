package com.ERP.authentification.services;

import com.ERP.authentification.Models.Subscription;
import com.ERP.authentification.Models.Team;
import com.ERP.authentification.repositories.SubscriptionRepository;
import com.ERP.authentification.repositories.TeamRepository;
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
public class TeamService {
    @Autowired
    private TeamRepository teamRepository ;


    public List<Team> findAll(){
        return this.teamRepository.findAll();

    }

    public Team  create(Team team ) {
        return this.teamRepository.save(team) ;

    }
}
