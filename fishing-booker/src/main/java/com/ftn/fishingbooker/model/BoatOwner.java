package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.BoatOwnerType;
import lombok.Data;

import javax.persistence.Entity;
import java.util.*;

@Entity
@Data
public class BoatOwner extends User {
    private BoatOwnerType type;
    private List<Boat> boats;
    private List<Report> reservationReport;
    private List<Complaint> complaints;
}
