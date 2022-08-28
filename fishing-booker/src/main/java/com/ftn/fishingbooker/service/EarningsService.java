package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.Earnings;
import com.ftn.fishingbooker.model.Reservation;

import java.util.Collection;
import java.util.Date;

public interface EarningsService {

    Collection<Earnings> getAllInDateRange(Date from, Date to);

    Collection<Earnings> getAllForAdvertiserInDateRange(Date from, Date to, String email);

    void saveEarnings(Reservation reservation, String email);
}
