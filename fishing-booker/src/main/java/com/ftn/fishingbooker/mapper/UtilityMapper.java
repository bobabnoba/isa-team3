package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.UtilityDto;
import com.ftn.fishingbooker.model.Utility;
import com.ftn.fishingbooker.model.home.VacationHomeUtility;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UtilityMapper {
    public static Set<UtilityDto> map(Set<VacationHomeUtility> utilities) {
        Set<UtilityDto> utilitiesDto = new HashSet<>();

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
