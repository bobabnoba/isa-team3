package com.ftn.fishingbooker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ftn.fishingbooker.enumeration.BoatType;
import com.ftn.fishingbooker.enumeration.CancelingCondition;
import com.ftn.fishingbooker.enumeration.NavigationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Boat {
    @Id
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

    private Integer guestLimit;

    private double pricePerDay = 0.0;

    private String description;

    private String information;

    @Column
//    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<NavigationType> navigationType;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(targetEntity = Image.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Image> images;

    @OneToMany(targetEntity = Reservation.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    private Set<Reservation> reservations;

    @ManyToMany(targetEntity = Utility.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Utility> utilities;

    @ManyToMany(targetEntity = Rule.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Rule> codeOfConduct;

    @ManyToMany(targetEntity = FishingEquipment.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<FishingEquipment> fishingEquipment;

//    @Enumerated(EnumType.STRING)
//    private CancelingCondition reservationCanceling;
    private double cancelingPercentage;

    @ManyToOne
    @JoinColumn(name = "boat_owner_id")
    @ToString.Exclude
    @JsonManagedReference
    private BoatOwner boatOwner;

    @OneToMany(targetEntity = BoatAvailability.class, mappedBy = "boat", fetch = FetchType.EAGER)
    private Set<BoatAvailability> availableTimePeriods;

    @Transient
    public List<String> getImagePaths() {
        List<String> retVal = new ArrayList<>();
        if (this.getImages() != null) {
            this.getImages().forEach(
                    image ->
                            retVal.add("/images/boats/" + this.getId() + "/" + image.getUrl()));
        }
        return retVal;
    }

    @OneToMany(targetEntity = SpecialOffer.class, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<SpecialOffer> specialOffers;
}
