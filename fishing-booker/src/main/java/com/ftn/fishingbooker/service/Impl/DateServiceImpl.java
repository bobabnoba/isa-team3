package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.VacationHomeAvailability;
import com.ftn.fishingbooker.service.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class DateServiceImpl implements DateService {
    @Override
    public boolean doPeriodsOverlap(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
        return !(endDate1.before(startDate2) || endDate2.before(startDate1));

    }

    @Override
    public boolean doPeriodsOverlap(Date startDate, Date endDate, Set<VacationHomeAvailability> availableTimePeriods) {

        for (VacationHomeAvailability period : availableTimePeriods) {
            if (doPeriodsOverlap(period.getStartDate(), period.getEndDate(),startDate, endDate)) {
                return true;
            }
        }
        return false;
    }
}
