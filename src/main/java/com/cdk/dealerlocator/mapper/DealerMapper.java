package com.cdk.dealerlocator.mapper;

import com.cdk.dealerlocator.dto.DealerDto;
import com.cdk.dealerlocator.entity.Dealer;

public class DealerMapper {

    public static DealerDto mapToDealerDto(Dealer dealer) {
        return new DealerDto(
                dealer.getId(),
                dealer.getName(),
                dealer.getAddress(),
                dealer.getCity(),
                dealer.getState(),
                dealer.getZipCode(),
                dealer.getLatitude(),
                dealer.getLongitude()
        );
    }

    public static Dealer mapToDealer(DealerDto dealerDto) {
        return new Dealer(
                dealerDto.getId(),
                dealerDto.getName(),
                dealerDto.getAddress(),
                dealerDto.getCity(),
                dealerDto.getState(),
                dealerDto.getZipCode(),
                dealerDto.getLatitude(),
                dealerDto.getLongitude()
        );
    }
}
