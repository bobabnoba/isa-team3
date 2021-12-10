package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class Instructor extends User {

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Adventure> adventures;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private Set<Report> reservationReport;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private Set<Complaint> complaints;
}
