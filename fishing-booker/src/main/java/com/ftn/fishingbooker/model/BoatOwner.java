package com.ftn.fishingbooker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.fishingbooker.enumeration.BoatOwnerType;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
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
    @JsonIgnore
    private Set<Boat> boats;

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Report> reservationReport;

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Complaint> complaints;


}
