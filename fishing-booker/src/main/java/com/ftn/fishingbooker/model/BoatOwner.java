package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.BoatOwnerType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Report> reservationReport;

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Complaint> complaints;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BoatOwner boatOwner = (BoatOwner) o;
        return getId() != null && Objects.equals(getId(), boatOwner.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
