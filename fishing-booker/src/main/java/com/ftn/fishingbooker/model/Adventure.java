package com.ftn.fishingbooker.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Adventure {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    private Address address;

    private String description;

    private double rating = 0.0;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> images;

    //TODO: quick reservations

//    @OneToMany(mappedBy = "adventure", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private Set<Reservation> availableReservations;

    private String codeOfConduct;

    private double pricePerDay;

    private double cancelingPercentage;

    private int maxNumberOfParticipants;

    @ManyToOne(targetEntity = Instructor.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Instructor instructor;


    @Column
    @ManyToMany(targetEntity = FishingEquipment.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<FishingEquipment> fishingEquipment;

    @Column
    @OneToMany(targetEntity = AdditionalService.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AdditionalService> additionalServices;


}
