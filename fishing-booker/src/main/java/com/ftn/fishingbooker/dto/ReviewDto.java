package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.ReservationType;

public class ReviewDto {

    public Long id;

    public String review;

    public Double ownerRating;

    public Double rentalRating;

    public ReservationType reservationType;

    public String ownerEmail;

    public String rentalName;

    public String clientEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getOwnerRating() {
        return ownerRating;
    }

    public void setOwnerRating(Double ownerRating) {
        this.ownerRating = ownerRating;
    }

    public Double getRentalRating() {
        return rentalRating;
    }

    public void setRentalRating(Double rentalRating) {
        this.rentalRating = rentalRating;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getRentalName() {
        return rentalName;
    }

    public void setRentalName(String rentalName) {
        this.rentalName = rentalName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}
