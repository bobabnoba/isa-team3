package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.*;
import lombok.*;

import java.util.*;

@Data
public class BoatInfo {
    public String name;
    public String type;
    public float length;
    public int engineCount;
    public int enginePower;
    public float maxSpeed;
    public double pricePerDay;
    public String description;
    public String information;
    public int guestLimit;
    public double cancelingPercentage;
}
