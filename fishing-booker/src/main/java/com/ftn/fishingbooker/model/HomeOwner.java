package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class HomeOwner extends User {

    @OneToMany(mappedBy="homeOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VacationHome> vacationHomes;

    @OneToMany(mappedBy="homeOwner", fetch = FetchType.LAZY)
    private Set<Report> reservationReport;

    @OneToMany(mappedBy="homeOwner", fetch = FetchType.LAZY)
    private Set<Complaint> complaints;
}
