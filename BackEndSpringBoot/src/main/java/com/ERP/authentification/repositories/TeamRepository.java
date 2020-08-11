package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Message;
import com.ERP.authentification.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long > {
    @Query("select t from Team t where t.id in (select s.idTeam from Subscription s where s.idUser= ?1 )")
    //@Query("select e from Employee e where e.id in ( select s.idUser from Subscription s where s.idTeam =  ?1 ) ")
    public List<Team> getAllemployeeFromTeamId(Long id ) ;
}
