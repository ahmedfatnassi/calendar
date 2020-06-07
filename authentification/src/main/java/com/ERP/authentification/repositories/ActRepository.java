package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Act;
import com.ERP.authentification.Models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActRepository  extends JpaRepository<Act, Long> {
}
