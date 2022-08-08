package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.BoatType;
import com.ftn.fishingbooker.enumeration.CancelingCondition;
import com.ftn.fishingbooker.enumeration.FishingEquipment;
import com.ftn.fishingbooker.enumeration.NavigationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Boat {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean deleted = false;

    private double rating = 0.0;

    @Enumerated(EnumType.STRING)
    private BoatType type;

    private float length;

    private int engineCount;

    private int enginePower;

    private float maxSpeed;

    private int capacity;

    private double pricePerDay = 0.0;

    private String description;

    private String information;

    @Column
    @ElementCollection(targetClass = Integer.class)
    private Set<NavigationType> navigationType;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(targetEntity = Image.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Image> images;

    @OneToMany(targetEntity = Reservation.class, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<Reservation> availableReservations;

    @OneToMany(targetEntity = Utility.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Utility> utilities;

    @Column
    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    private Set<String> codeOfConduct;

    @Column
    @ElementCollection(targetClass = Integer.class)
    private Set<FishingEquipment> fishingEquipment;

    @Enumerated(EnumType.STRING)
    private CancelingCondition reservationCanceling;

    @ManyToOne
    @JoinColumn(name = "boat_owner_id")
    private BoatOwner boatOwner;

}
