package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.FishingEquipmentDto;
import com.ftn.fishingbooker.dto.NewSpecialOfferDto;
import com.ftn.fishingbooker.dto.SpecialOfferDto;
import com.ftn.fishingbooker.model.SpecialOffer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
        entity.setCaptain(dto.isCaptain());
        return entity;
    }

    public static Collection<SpecialOfferDto> toDtoSet(Set<SpecialOffer> specialOffers){
        Collection<SpecialOfferDto> dtos = new ArrayList<>();
        if (specialOffers != null) {
            specialOffers.forEach(specialOffer -> {

                SpecialOfferDto dto = new SpecialOfferDto();
                dto.setId(specialOffer.getId());
                dto.setDiscount(specialOffer.getDiscount());
                dto.setPrice(specialOffer.getPrice());
                dto.setActiveFrom(specialOffer.getActiveFrom());
                dto.setActiveTo(specialOffer.getActiveTo());
                dto.setType(specialOffer.getType());
                dto.setReservationStartDate(specialOffer.getReservationStartDate());
                dto.setReservationEndDate(specialOffer.getReservationEndDate());
                dto.setGuests(specialOffer.getGuests());
                dto.setUtilities(UtilityMapper.map(specialOffer.getUtilities()));
                dto.setCaptain(specialOffer.isCaptain());
                dtos.add(dto);
            });
        }

        return dtos;
    }
}
