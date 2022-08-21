package com.ftn.fishingbooker.dto;

public class AdventureInfo {
    public String title;
    public String description;
    public double price;
    public double durationInHours;
    public int maxParticipants;
    public int cancelingPercentage;

    public String getTitle() {
        return title;
    }

    public double getDurationInHours() {
        return durationInHours;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public int getCancelingPercentage() {
        return cancelingPercentage;
    }
}
