package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.BoatAvailability;
import com.ftn.fishingbooker.model.VacationHomeAvailability;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public interface DateService {
    boolean doPeriodsOverlap(Date startDate, Date endDate, Date startDate1, Date endDate1);

    long DifferenceBetweenDates(Date startDate, Date endDate) throws ParseException;

    Date addHoursToJavaUtilDate(Date startDate, double durationInHours);
}
