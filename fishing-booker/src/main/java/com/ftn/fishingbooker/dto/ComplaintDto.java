package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.ReservationType;
import lombok.Data;

@Data
public class ComplaintDto {

    private Long reservationId;

    private String complaint;

    private ReservationType reservationType;

}
