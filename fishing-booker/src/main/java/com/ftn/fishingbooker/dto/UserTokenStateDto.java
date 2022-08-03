package com.ftn.fishingbooker.dto;

import lombok.Data;

@Data
public class UserTokenStateDto {

    private String jwt;
    private Long expiresIn;

    public UserTokenStateDto(String jwt, long expiresIn) {
        this.jwt = jwt;
        this.expiresIn = expiresIn;
    }

}
