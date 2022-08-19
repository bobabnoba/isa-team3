package com.ftn.fishingbooker.dto;


public class UserRankDto {

    public Long id;

    public String name;

    public int minPoints;

    public double reservationPercentage;

    public double percentage;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public double getReservationPercentage() {
        return reservationPercentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }

    public void setReservationPercentage(double reservationPercentage) {
        this.reservationPercentage = reservationPercentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
