package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class HomeOwner extends User {

    @OneToMany(mappedBy="homeOwner")
    private List<VacationHome> vacationHomes;

    @OneToMany(mappedBy="homeOwner")
    private List<Report> reservationReport;

    @OneToMany(mappedBy="homeOwner")
    private List<Complaint> complaints;
}
