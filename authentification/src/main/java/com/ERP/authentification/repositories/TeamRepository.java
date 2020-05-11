package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Message;
import com.ERP.authentification.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long > {
}
