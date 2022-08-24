package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.service.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class DateServiceImpl implements DateService {
    @Override
    public boolean doPeriodsOverlap(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
        return !(endDate1.before(startDate2) || endDate2.before(startDate1));

    }

    @Override
    public long DifferenceBetweenDates(Date startDate, Date endDate) throws ParseException {

        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return diff;
    }

    @Override
    public Date addHoursToJavaUtilDate(Date date, double hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, (int) hours);
        return calendar.getTime();
    }

    @Override
    public Date addDaysToJavaUtilDate(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);  // number of days to add
        return c.getTime();
    }
}
