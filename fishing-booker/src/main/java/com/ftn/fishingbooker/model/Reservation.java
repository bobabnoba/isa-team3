package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.ReservationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isCancelled = false;

    @Enumerated(EnumType.STRING)
    private ReservationType type;

    private Date startDate;

    private Date endDate;

    private int guests;

    private double price;

    @ManyToMany(targetEntity = Utility.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Utility> utilities;

    @ManyToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToOne(targetEntity = Report.class, cascade = CascadeType.ALL)
    private Report report;

}
