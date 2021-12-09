package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Instructor extends User {

    @OneToMany(mappedBy = "instructor")
    private List<Adventure> adventures;

    @OneToMany(mappedBy = "instructor")
    private List<Report> reservationReport;

    @OneToMany(mappedBy = "instructor")
    private List<Complaint> complaints;
}
