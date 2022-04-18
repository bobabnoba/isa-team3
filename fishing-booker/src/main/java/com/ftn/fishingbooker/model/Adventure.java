package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.CancelingCondition;
import com.ftn.fishingbooker.enumeration.FishingEquipment;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashMap;
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

    private String name;

    private String address;

    private String description;

    //private Set<Base64> images;

    @OneToMany(mappedBy = "adventure", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Reservation> availableReservations;

    private String codeOfConduct;

    private HashMap<String, Float> priceList;

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
