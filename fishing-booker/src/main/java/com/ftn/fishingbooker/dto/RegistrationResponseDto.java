package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import lombok.Data;

@Data
public class RegistrationResponseDto {

    private Long id;
    private String response;
    private boolean approved;
    private String userEmail;
    private RegistrationType type;
    private String motivation;


}
