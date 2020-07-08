package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Agent;
import com.ERP.authentification.Models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository  extends JpaRepository<Request, Long> {
    public List<Request> findAllByIsArchivedIsTrue();
}
