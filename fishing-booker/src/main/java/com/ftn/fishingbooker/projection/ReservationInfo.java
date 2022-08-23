package com.ftn.fishingbooker.projection;

import java.util.Date;

public interface ReservationInfo {

    Long getId();

    Date getStartDate();

    Double getDurationInHours();

    Double getPrice();

    Integer getGuests();
}
