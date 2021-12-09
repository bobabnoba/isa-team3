package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.BoatOwnerType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class BoatOwner extends User {

    @Enumerated(EnumType.STRING)
    private BoatOwnerType type;

    @OneToMany(mappedBy = "boatOwner")
    private List<Boat> boats;

    @OneToMany(mappedBy = "boatOwner")
    private List<Report> reservationReport;

    @OneToMany(mappedBy = "boatOwner")
    private List<Complaint> complaints;
}
