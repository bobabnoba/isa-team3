package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.UtilityDto;
import com.ftn.fishingbooker.model.Utility;

import java.util.*;

public class UtilityMapper {

    public static Collection<UtilityDto> map(Collection<Utility> utilities) {
        Collection<UtilityDto> utilitiesDto = new ArrayList<>();

        for (Utility utility : utilities
        ) {
            UtilityDto utilityDto = new UtilityDto();
            utilityDto.setId(utility.getId());
            utilityDto.setName(utility.getName());
            utilityDto.setPrice(utility.getPrice());

            utilitiesDto.add(utilityDto);
        }
        return utilitiesDto;
    }

    public static Set<Utility> toEntitySet(Collection<UtilityDto> dtos) {
            Set<Utility> entities = new HashSet<>();
            dtos.forEach(dto -> entities.add(new Utility(dto.getId(), dto.getName(), dto.getPrice())));
            return entities;
    }

}
