package com.ERP.authentification.services;

import com.ERP.authentification.Models.Employee;
import com.ERP.authentification.Models.Subscription;
import com.ERP.authentification.repositories.SubscriptionRepository;
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
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository ;


    public List<Subscription> findAll(){
        return this.subscriptionRepository.findAll();

    }
    public List<Employee> findAllEmployeeByteamID(Long id ){
        return this.subscriptionRepository.getusersByTeam(id);

    }
    public void deletebyteamIdAndUserID(Long teamid ,Long userid ){

        this.subscriptionRepository.deleteByIdTeamAndIdUser(teamid,userid);

    }
    public void deleteAllbyteamId(Long teamid  ){

        this.subscriptionRepository.deleteAllByIdTeam(teamid);

    }
    public Subscription  create(Subscription subscription ) {
        return this.subscriptionRepository.save(subscription) ;

    }
}
