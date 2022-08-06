package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.UtilityDto;
import com.ftn.fishingbooker.model.Utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class UtilityMapper {
    public static Collection<UtilityDto> map(Set<Utility> utilities) {
        Collection<UtilityDto> utilitiesDto = new ArrayList<>();

        for (Utility utility : utilities
        ) {
            UtilityDto utilityDto = new UtilityDto();
            utilityDto.setName(utility.getName());
            utilityDto.setPrice(utility.getPrice());

            utilitiesDto.add(utilityDto);
        }
        return utilitiesDto;
    }
}
