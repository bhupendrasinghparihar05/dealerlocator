package com.cdk.dealerlocator.service;

import com.cdk.dealerlocator.dto.DealerDto;
import com.cdk.dealerlocator.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealerService {
    DealerDto createDealer(DealerDto dealerDto);

    DealerDto getDealerById(Long dealerId);

    List<DealerDto> getAllDealers();

    DealerDto updateDealer(Long dealerId, DealerDto dealerDto);

    void deleteDealer(Long dealerId);
}
