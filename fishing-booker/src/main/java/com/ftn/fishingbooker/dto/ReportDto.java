package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.ReservationType;

public class ReportDto {

    public Long id;
    public String comment;
    public boolean clientShowedUp;
    public String ownerEmail;
    public ReservationType reportType;

    public void setId(Long id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setClientShowedUp(boolean clientShowedUp) {
        this.clientShowedUp = clientShowedUp;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public void setReportType(ReservationType reportType) {
        this.reportType = reportType;
    }
}