package com.ftn.fishingbooker.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class HomeOwner extends User {

    @OneToMany(mappedBy="homeOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<VacationHome> vacationHomes;

    @OneToMany(mappedBy="homeOwner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Report> reservationReport;

    @OneToMany(mappedBy="homeOwner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Complaint> complaints;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HomeOwner homeOwner = (HomeOwner) o;
        return getId() != null && Objects.equals(getId(), homeOwner.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
