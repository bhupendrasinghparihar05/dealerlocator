package com.cdk.dealerlocator.repo;

import com.cdk.dealerlocator.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DealerRepository extends JpaRepository<Dealer, Long> {

    List<Dealer> findByZipCode(String zipCode);
}
