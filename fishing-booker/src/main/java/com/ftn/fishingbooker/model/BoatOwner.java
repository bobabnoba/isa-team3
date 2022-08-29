package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.BoatOwnerType;
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

    @Enumerated(EnumType.STRING)
    private BoatOwnerType type;

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Boat> boats;

    @OneToMany(targetEntity = BoatOwnerAvailability.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<BoatOwnerAvailability> availability;

}
