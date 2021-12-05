package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.*;

@Entity
@Data
public class HomeOwner extends User {
    private List<VacationHome> vacationHomes;
    private List<Report> reservationReport;
    private List<Complaint> complaints;
}
