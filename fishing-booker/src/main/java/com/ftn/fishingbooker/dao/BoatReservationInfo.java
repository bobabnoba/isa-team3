package com.ftn.fishingbooker.dao;

import java.util.*;

public interface BoatReservationInfo {
    Long getId();

    Date getStartDate();

    Date getEndDate();

    Double getPrice();

    String getBoatName();
}
