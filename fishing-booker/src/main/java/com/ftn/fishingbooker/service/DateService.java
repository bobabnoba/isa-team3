package com.ftn.fishingbooker.service;

import java.text.ParseException;
import java.util.Date;

public interface DateService {

    boolean doPeriodsOverlap(Date startDate, Date endDate, Date startDate1, Date endDate1);

    long DifferenceBetweenDates(Date startDate, Date endDate) throws ParseException;

    Date addHoursToJavaUtilDate(Date startDate, double durationInHours);

    Date addDaysToJavaUtilDate(Date date, int days);

    boolean reservationOverlapsWithAvailability(Date resStartDate, Date resEndDate, Date availStartDate, Date availEndDate);

    boolean inBetweenOrEqual(Date startDate1, Date endDate1, Date startDate2, Date endDate2);
}
