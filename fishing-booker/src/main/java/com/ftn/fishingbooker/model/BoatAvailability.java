package com.ftn.fishingbooker.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "boat_availability_periods")
public class BoatAvailability {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;
//
//    @ManyToOne
//    @JoinColumn(name = "boat_id", nullable = false)
//    @ToString.Exclude
//    private Boat boat;
}
