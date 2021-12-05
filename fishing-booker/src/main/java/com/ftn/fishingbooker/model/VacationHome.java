package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Entity
@Data
public class VacationHome {
    private String name;
    private String address;
    private String description;
    private List<Base64> images;
    private HashMap<Integer, Integer> bedRoom;
    private List<Reservation> availableReservations;
    private String codeOfConduct;
    private HashMap<String, Float> priceList;
    private String information;
}
