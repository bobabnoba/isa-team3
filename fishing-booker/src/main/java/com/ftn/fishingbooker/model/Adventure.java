package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.CancelingCondition;
import com.ftn.fishingbooker.enumeration.FishingEquipment;
import lombok.Data;

import javax.persistence.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Adventure {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String address;

    private String description;

//    private Set<Base64> images;

    @OneToMany(mappedBy = "adventure")
    private List<Reservation> availableReservations;

    private String codeOfConduct;

    private HashMap<String, Float> priceList;

    private String information;

    @Column
    @ElementCollection(targetClass=Integer.class)
    private List<FishingEquipment> fishingEquipment;

//    @Column
//    @ElementCollection(targetClass=Integer.class)
    private CancelingCondition reservationCanceling;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
}
