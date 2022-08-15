package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.NewSpecialOfferDto;
import com.ftn.fishingbooker.model.SpecialOffer;

import java.util.HashSet;

public class SpecialOfferMapper {

    public static SpecialOffer toNewEntity(NewSpecialOfferDto dto){
        SpecialOffer entity = new SpecialOffer();
        entity.setDiscount(dto.getDiscount());
        entity.setPrice(dto.getPrice());
        entity.setActiveFrom(dto.getActiveFrom());
        entity.setActiveTo(dto.getActiveTo());
        entity.setType(dto.getType());
        entity.setReservationStartDate(dto.getReservationStartDate());
        entity.setReservationEndDate(dto.getReservationEndDate());
        entity.setGuests(dto.getGuests());
        entity.setUtilities(UtilityMapper.toEntitySet(dto.getUtilities()));
        return entity;
    }
}
