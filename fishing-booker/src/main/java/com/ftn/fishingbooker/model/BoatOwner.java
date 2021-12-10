package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.BoatOwnerType;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class BoatOwner extends User {

    @Enumerated(EnumType.STRING)
    private BoatOwnerType type;

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Boat> boats;

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY)
    private Set<Report> reservationReport;

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY)
    private Set<Complaint> complaints;
}
