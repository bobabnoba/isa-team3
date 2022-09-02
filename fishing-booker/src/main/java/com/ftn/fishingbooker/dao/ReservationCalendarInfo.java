package com.ftn.fishingbooker.dao;

import java.util.Date;

public interface ReservationCalendarInfo {

    Date getStartDate();

    Date getEndDate();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getTitle();
}
