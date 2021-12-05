package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.AdditionalService;
import com.ftn.fishingbooker.enumeration.ReservationType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Reservation {
    @Id
    private int id;
    private ReservationType type;
    private Date startDate;
    private int days;
    private int maxGuests;
    private List<AdditionalService> additionalServices;
    private float price;
    private String adventureDestination;
    private int clientId;
}
