package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.*;
import lombok.*;
import org.springframework.web.multipart.*;

import javax.persistence.*;
import java.util.*;

public class VacationHomeDto {
    public Long id;
    public String name;
    public String address;
    public String description;
    public List<byte[]> images;
    //public HashMap<Integer, Integer> bedRoom;
    //public Set<Reservation> availableReservations;
    public String codeOfConduct;
    //public HashMap<String, Float> priceList;
    public String information;

}
