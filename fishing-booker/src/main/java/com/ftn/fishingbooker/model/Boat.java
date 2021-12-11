package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.BoatType;
import com.ftn.fishingbooker.enumeration.CancelingCondition;
import com.ftn.fishingbooker.enumeration.FishingEquipment;
import com.ftn.fishingbooker.enumeration.NavigationType;
import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Set;

@Data
@Entity
public class Boat {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private BoatType type;

    private float length;

    private int engineCount;

    private int enginePower;

    private float maxSpeed;

    @Column
    @ElementCollection(targetClass=Integer.class)
    private Set<NavigationType> navigationType;

    private String address;

    private String description;

    //TODO: Set of images
    //private Set<Base64> images;

    private int capacity;

    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY)
    private Set<Reservation> availableReservations;

    private String codeOfConduct;

    @Column
    @ElementCollection(targetClass=Integer.class)
    private Set<FishingEquipment> fishingEquipment;

    @Enumerated(EnumType.STRING)
    private CancelingCondition reservationCanceling;

    private HashMap<String, Float> priceList;

    private String information;

    @ManyToOne
    @JoinColumn(name = "boat_owner_id")
    private BoatOwner boatOwner;


}
