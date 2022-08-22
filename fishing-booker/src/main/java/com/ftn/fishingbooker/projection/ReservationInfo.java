package com.ftn.fishingbooker.projection;

import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.Reservation;

import java.util.Date;

public interface ReservationInfo {

    Long getId();
    Date getStartDate();
    Double getDurationInHours();
    Double getPrice();
    Integer getGuests();
}
