package com.ftn.fishingbooker.service;

import java.text.ParseException;
import java.util.Date;

public interface DateService {
    boolean doPeriodsOverlap(Date startDate, Date endDate, Date startDate1, Date endDate1);

    long DifferenceBetweenDates(Date startDate, Date endDate) throws ParseException;

    Date addHoursToJavaUtilDate(Date startDate, double durationInHours);

    Date addDaysToJavaUtilDate(Date date, int days);
}
