package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.ReservationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;
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

    private double discount;

    private Boolean isCancelled = false;

    @Enumerated(EnumType.STRING)
    private ReservationType type;

    private Date startDate;

    private Date endDate;

    private int maxGuests;

    private float price;

    @ManyToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

}
