package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.UserRankDto;
import com.ftn.fishingbooker.model.UserRank;

public class UserRankMapper {

    public static UserRankDto toDto(UserRank userRank) {
        UserRankDto dto = new UserRankDto();
        dto.setId(userRank.getId());
        dto.setName(userRank.getName());
        dto.setMinPoints(userRank.getMinPoints());
        dto.setPercentage(userRank.getPercentage());
        dto.setReservationPercentage(userRank.getReservationPercentage());
        return dto;
    }

    public static UserRank toEntity(UserRankDto dto) {
        UserRank userRank = new UserRank();
        userRank.setId(dto.getId());
        userRank.setName(dto.getName());
        userRank.setMinPoints(dto.getMinPoints());
        userRank.setPercentage(dto.getPercentage());
        userRank.setReservationPercentage(dto.getReservationPercentage());
        return userRank;
    }
}
