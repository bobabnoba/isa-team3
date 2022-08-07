package com.ftn.fishingbooker.model;
import lombok.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    //TODO: location class, map related
    private String address;

    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Adventure adventure = (Adventure) o;
        return id != null && Objects.equals(id, adventure.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
