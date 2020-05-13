package com.ERP.authentification.services;

import com.ERP.authentification.Models.Employee;
import com.ERP.authentification.Models.Subscription;
import com.ERP.authentification.Models.Task;
import com.ERP.authentification.repositories.SubscriptionRepository;
import com.ERP.authentification.repositories.TaskRepository;
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

    public Subscription  create(Subscription subscription ) {
        return this.subscriptionRepository.save(subscription) ;

    }
}
