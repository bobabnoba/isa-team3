package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.ReservationType;

public class NewReportDto {

    public String comment;
    public boolean clientShowedUp;
    public String ownerEmail;
    public String clientEmail;
    public ReservationType type;

    public ReservationType getType() {
        return type;
    }
    public String getClientEmail() {
        return clientEmail;
    }

    public String getComment() {
        return comment;
    }

    public boolean isClientShowedUp() {
        return clientShowedUp;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }
}
