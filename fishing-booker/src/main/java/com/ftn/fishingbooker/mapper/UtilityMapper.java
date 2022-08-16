package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.UtilityDto;
import com.ftn.fishingbooker.model.Utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UtilityMapper {

    public static Collection<UtilityDto> map(Collection<Utility> utilities) {
        Collection<UtilityDto> utilitiesDto = new ArrayList<>();

        for (Utility utility : utilities
        ) {
            UtilityDto utilityDto = map(utility);
            utilitiesDto.add(utilityDto);
        }
        return utilitiesDto;
    }
    public static Set<Utility> mapToModel(Set<UtilityDto> utilitiesDto) {
        Set<Utility> utilities = new HashSet<>();

        for (UtilityDto utilityDto : utilitiesDto
        ) {
            Utility utility = map(utilityDto);
            utilities.add(utility);
        }
        return utilities;
    }

    public static UtilityDto map(Utility utility) {
        UtilityDto utilityDto = new UtilityDto();
        utilityDto.setName(utility.getName());
        utilityDto.setPrice(utility.getPrice());

        return utilityDto;
    }

    public static Utility map(UtilityDto utilityDto) {
        Utility utility = new Utility();
        utility.setName(utilityDto.getName());
        utility.setPrice(utilityDto.getPrice());

        return utility;
    }

    public static Set<Utility> toEntitySet(Collection<UtilityDto> dtos) {
            Set<Utility> entities = new HashSet<>();
            dtos.forEach(dto -> entities.add(new Utility(dto.getId(), dto.getName(), dto.getPrice())));
            return entities;
    }

}
