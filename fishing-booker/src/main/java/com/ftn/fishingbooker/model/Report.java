package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Report {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private int reservationId;

    private String text;

    @ManyToOne
    @JoinColumn(name = "boat_owner_id")
    private BoatOwner boatOwner;

    @ManyToOne
    @JoinColumn(name = "home_owner_id")
    private HomeOwner homeOwner;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;


}
