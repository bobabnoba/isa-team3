package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
public class Instructor extends User {
    private List<Adventure> adventures;
    private List<Report> reservationReport;
    private List<Complaint> complaints;
}
