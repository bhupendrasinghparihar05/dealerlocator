package com.cdk.dealerlocator.service.impl;

import com.cdk.dealerlocator.dto.DealerDto;
import com.cdk.dealerlocator.entity.Dealer;
import com.cdk.dealerlocator.exception.ResourceNotFoundException;
import com.cdk.dealerlocator.mapper.DealerMapper;
import com.cdk.dealerlocator.service.DealerService;
import com.cdk.dealerlocator.repo.DealerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DealerServiceImpl implements DealerService {

    private DealerRepository dealerRepository;

    @Override
    public DealerDto createDealer(DealerDto dealerDto) {
        Dealer dealer = DealerMapper.mapToDealer(dealerDto);
        Dealer savedDealer = dealerRepository.save(dealer);
        return DealerMapper.mapToDealerDto(savedDealer);
    }

    @Override
    public DealerDto getDealerById(Long dealerId) {
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer does not exist with given id : " + dealerId));

        return DealerMapper.mapToDealerDto(dealer);
    }

    @Override
    public List<DealerDto> getAllDealers() {
        List<Dealer> dealers = dealerRepository.findAll();
        return dealers.stream().map((dealer) -> DealerMapper.mapToDealerDto(dealer))
                .collect(Collectors.toList());
    }

    @Override
    public DealerDto updateDealer(Long dealerId, DealerDto updatedDealer) {

        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer does not exist with given id : " + dealerId));

        dealer.setAddress(updatedDealer.getAddress());
        dealer.setCity(updatedDealer.getCity());
        dealer.setName(updatedDealer.getName());
        dealer.setState(updatedDealer.getState());
        dealer.setZipCode(updatedDealer.getZipCode());
        dealer.setLatitude(updatedDealer.getLatitude());
        dealer.setLongitude(updatedDealer.getLongitude());

        Dealer updatedDealerObj = dealerRepository.save(dealer);

        return DealerMapper.mapToDealerDto(updatedDealerObj);
    }

    @Override
    public void deleteDealer(Long dealerId) {

        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer does not exist with given id : " + dealerId));

        dealerRepository.deleteById(dealerId);
    }


}
