package com.ftn.fishingbooker.dto;

public class AdventureInfo {
    public String title;
    public String description;
    public double price;
    public int maxParticipants;
    public int cancelingPercentage;

    public String getTitle() {
        return title;
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
