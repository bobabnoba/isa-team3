package com.ftn.fishingbooker.model.boat;

import com.ftn.fishingbooker.enumeration.BoatType;
import com.ftn.fishingbooker.enumeration.CancelingCondition;
import com.ftn.fishingbooker.enumeration.FishingEquipment;
import com.ftn.fishingbooker.enumeration.NavigationType;
import com.ftn.fishingbooker.model.Image;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean deleted = false;

    private String name;

    private BoatType type;

    private float length;

    private int engineCount;

    private int enginePower;

    private float maxSpeed;

    @Column
    @ElementCollection(targetClass = Integer.class)
    private Set<NavigationType> navigationType;

    private String address;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "boat_images",
            joinColumns = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    private Double rating = 0.0;

    private int capacity;

    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<BoatReservation> availableReservations;

    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> codeOfConduct;

    @Column
    @ElementCollection(targetClass = Integer.class)
    private Set<FishingEquipment> fishingEquipment;

    private CancelingCondition reservationCanceling;


    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BoatUtility> utilities;

    private String information;

    @ManyToOne
    @JoinColumn(name = "boat_owner_id")
    private BoatOwner boatOwner;

}
