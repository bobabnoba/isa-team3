package com.ftn.fishingbooker.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "boat_owner_availability_periods")
public class BoatOwnerAvailability {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;
}
