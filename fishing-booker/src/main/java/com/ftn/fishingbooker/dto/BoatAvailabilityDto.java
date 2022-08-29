package com.ftn.fishingbooker.dto;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
public class BoatAvailabilityDto {
    public Long id;
    public Date startDate;
    public Date endDate;
}
