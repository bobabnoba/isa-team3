package com.ftn.fishingbooker.model.boat;

import com.ftn.fishingbooker.enumeration.BoatOwnerType;
import com.ftn.fishingbooker.model.Complaint;
import com.ftn.fishingbooker.model.Report;
import com.ftn.fishingbooker.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BoatOwner extends User {

    private BoatOwnerType type;

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Boat> boats;

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Report> reservationReport;

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Complaint> complaints;


}
