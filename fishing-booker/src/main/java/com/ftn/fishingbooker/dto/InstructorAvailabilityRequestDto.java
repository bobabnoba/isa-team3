package com.ftn.fishingbooker.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class InstructorAvailabilityRequestDto {

    public Date startDate;

    public Date endDate;

    public String instructorEmail;

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

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }
}
