package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.Earnings;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.UserRank;

import java.util.Collection;
import java.util.Date;

public interface EarningsService {

    Collection<Earnings> getAllInDateRange(Date from, Date to);

    Collection<Earnings> getAllForAdvertiserInDateRange(Date from, Date to, String email);

    void saveApplicationEarnings(Reservation reservation, String email, UserRank advertiserRank);

    void deleteApplicationEarnings(Long reservationId);
}
