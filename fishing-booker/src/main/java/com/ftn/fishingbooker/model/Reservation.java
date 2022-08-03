package com.ftn.fishingbooker.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Reservation {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startDate;

    //TODO:trazi se trajanje rezervacije?

    private Date endDate;

    private int maxGuests;

    private Boolean isCanceled = false;

    private float price;

    @Column
    @ElementCollection(targetClass = Integer.class)
    private Set<String> additionalServices;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Reservation that = (Reservation) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
