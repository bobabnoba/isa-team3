package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.CancelingCondition;
import com.ftn.fishingbooker.enumeration.FishingEquipment;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Entity
@Data
public class Adventure {
    private String name;
    private String address;
    private String description;
    private List<Base64> images;
    private List<Reservation> availableReservations;
    private String codeOfConduct;
    private HashMap<String, Float> priceList;
    private String information;
    private List<FishingEquipment> fishingEquipment;
    private CancelingCondition reservationCanceling;
}
