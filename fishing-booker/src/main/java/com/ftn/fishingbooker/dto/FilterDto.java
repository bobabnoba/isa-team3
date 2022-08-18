package com.ftn.fishingbooker.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FilterDto {
    private String email;
    private Integer people;
    private Date startDate;
    private Date endDate;
}
