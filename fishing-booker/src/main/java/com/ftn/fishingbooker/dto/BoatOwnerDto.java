package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.*;
import lombok.*;

import java.util.*;

@Data
public class BoatOwnerDto {
    public  UserDto user;
    public Collection<BoatOwnerAvailability> availability;
}
