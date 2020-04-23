package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Event;
import com.ERP.authentification.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository  extends JpaRepository<Task, Long> {
}
