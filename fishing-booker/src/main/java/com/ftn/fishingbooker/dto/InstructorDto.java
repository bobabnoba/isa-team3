package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.Address;
import lombok.Data;

@Data
public class InstructorDto {
    private Long id;

    private String firstName;

    private String lastName;

    private double rating;

    private String email;

    private Address address;

    private String biography;

}
