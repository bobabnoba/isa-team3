package com.ftn.fishingbooker.model.adventure;

import com.ftn.fishingbooker.enumeration.CancelingCondition;
import com.ftn.fishingbooker.enumeration.FishingEquipment;
import com.ftn.fishingbooker.model.Image;
import com.ftn.fishingbooker.model.Instructor;
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
public class Adventure {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean deleted = false;

    private String name;

    private String address;

    private Double rating = 0.0;

    private String description;

    @OneToMany(mappedBy = "adventure", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<AdventureReservation> availableReservations;

    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> codeOfConduct;

    @OneToMany(mappedBy = "adventure", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AdventureUtility> utilities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "adventure_images",
            joinColumns = @JoinColumn(name = "adventure_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;


    private String information;

    @Column
    @ElementCollection(targetClass = Integer.class)
    private Set<FishingEquipment> fishingEquipment;

    //@Column
    //@ElementCollection(targetClass=Integer.class)
    private CancelingCondition reservationCanceling;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

}
