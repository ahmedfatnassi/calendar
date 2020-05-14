package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Employee;
import com.ERP.authentification.Models.Message;
import com.ERP.authentification.Models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository  extends JpaRepository<Subscription, Long > {
    @Query("select e from Employee e where e.id in ( select s.idUser from Subscription s where s.idTeam =  ?1 ) ")
    public List<Employee> getusersByTeam(@Param("id") Long id ) ;
    public void deleteByIdTeamAndIdUser(Long teamid , Long userid) ;
}
