package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Message;
import com.ERP.authentification.Models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository  extends JpaRepository<Subscription, Long > {
}
