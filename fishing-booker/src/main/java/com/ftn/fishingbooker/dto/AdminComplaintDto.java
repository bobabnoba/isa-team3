package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.RentalType;
import com.ftn.fishingbooker.enumeration.ReservationType;


public class AdminComplaintDto {

    public Long id;

    public String complaint;

    public Long reservationId;

    public RentalType rentalType;

    public String ownerEmail;

    public Long rentalId;

    public String clientEmail;

    public String adminResponse;

    public ReservationType reservationType;

    public void setId(Long id) {
        this.id = id;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void setAdminResponse(String adminResponse) {
        this.adminResponse = adminResponse;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }
}
