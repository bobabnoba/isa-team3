package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.RentalType;
import com.ftn.fishingbooker.enumeration.ReservationType;


public class AdminComplaintDto {

    public Long id;

    public String complaint;

    public ReservationType reservationType;

    public String ownerEmail;

    public String clientEmail;

    public void setId(Long id) {
        this.id = id;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}
