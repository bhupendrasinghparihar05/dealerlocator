package com.cdk.dealerlocator.repo;

import com.cdk.dealerlocator.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DealerRepository extends JpaRepository<Dealer, Long> {
}
