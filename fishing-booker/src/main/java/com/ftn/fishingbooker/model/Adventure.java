package com.ftn.fishingbooker.model;

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
public class Adventure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    private Address address;

    private String description;

    private double rating = 0.0;

    private double pricePerDay = 0.0;

    @OneToMany(targetEntity = Image.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Image> images;

    //TODO: quick reservations
    @OneToMany(targetEntity = Reservation.class, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<Reservation> reservations;

    @OneToMany(targetEntity = Rule.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Rule> codeOfConduct;

    private double cancelingPercentage;

    private int maxNumberOfParticipants;

    @ManyToOne(targetEntity = Instructor.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Instructor instructor;

    @Column
    @ManyToMany(targetEntity = FishingEquipment.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<FishingEquipment> fishingEquipment;


    @OneToMany(targetEntity = Utility.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Utility> utilities;

    @Transient
    public List<String> getImagePaths() {
        List<String> retVal = new ArrayList<>();
        if (this.getImages() != null){
            this.getImages().forEach(
                    image ->
                            retVal.add("/images/adventures/" + this.getId() + "/" + image.getUrl()));
        }
        return retVal;
    }


}
