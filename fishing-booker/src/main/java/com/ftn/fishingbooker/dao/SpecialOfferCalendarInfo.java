package com.ftn.fishingbooker.dao;

import java.util.Date;

public interface SpecialOfferCalendarInfo {

    Date getActiveFrom();

    Date getActiveTo();

    Date getReservationStartDate();

    Date getReservationEndDate();

    String getTitle();
}
