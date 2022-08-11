package com.ftn.fishingbooker.service;

import java.time.LocalDateTime;
import java.util.Date;

public interface DateService {
    boolean doPeriodsOverlap(Date startDate, Date endDate, Date startDate1, Date endDate1);
}
