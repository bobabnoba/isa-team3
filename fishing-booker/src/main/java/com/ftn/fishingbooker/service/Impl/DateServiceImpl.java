package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.service.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class DateServiceImpl implements DateService {
    @Override
    public boolean doPeriodsOverlap(Date startDate, Date endDate, Date startDate1, Date endDate1) {
        return false;
    }
}
