package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.BoatType;
import com.ftn.fishingbooker.enumeration.CancelingCondition;
import com.ftn.fishingbooker.enumeration.FishingEquipment;
import com.ftn.fishingbooker.enumeration.NavigationType;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.List;

@Entity
@Data
public class Boat {
    private String name;
    private BoatType type;
    private float length;
    private int engineCount;
    private int enginePower;
    private float maxSpeed;
    private List<NavigationType> navigationType;
    private String address;
    private String description;
    private List<Base64> images;
    private int capacity;
    private List<Reservation> availableReservations;
    private String codeOfConduct;
    private List<FishingEquipment> fishingEquipment;
    private CancelingCondition reservationCanceling;
    private HashMap<String, Float> priceList;
    private String information;
}
