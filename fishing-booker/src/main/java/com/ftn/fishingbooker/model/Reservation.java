package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.AdditionalService;
import com.ftn.fishingbooker.enumeration.ReservationType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Reservation {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReservationType type;

    private Date startDate;

    private int days;

    private int maxGuests;

    @Column
    @ElementCollection(targetClass = Integer.class)
    private List<AdditionalService> additionalServices;

    private float price;

    private String adventureDestination;

    @ManyToOne
    @JoinColumn(name = "boat_id", nullable = false)
    private Boat boat;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;

    @ManyToOne
    @JoinColumn(name = "vacation_home_id")
    private VacationHome vacationHome;

}
