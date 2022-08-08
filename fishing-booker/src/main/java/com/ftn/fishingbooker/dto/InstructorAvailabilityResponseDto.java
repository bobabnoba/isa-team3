package com.ftn.fishingbooker.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class InstructorAvailabilityResponseDto {

    public Long id;

    public Date startDate;

    public Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
