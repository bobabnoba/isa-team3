package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.EarningsDto;
import com.ftn.fishingbooker.model.Earnings;

public class EarningsMapper {

    public static EarningsDto toDto(Earnings earnings){
        EarningsDto dto = new EarningsDto();

        dto.setAdvertiserEmail(earnings.getAdvertiserEmail());
        dto.setAmount(earnings.getAmount());
        dto.setTransactionDate(earnings.getTransactionDate());

        return dto;
    }
}
