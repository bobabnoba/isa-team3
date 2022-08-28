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
public class SpecialOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double discount;

    private double price;

    private Date activeFrom;

    private Date activeTo;

    @Enumerated(EnumType.STRING)
    private ReservationType type;

    private Date reservationStartDate;

    private Date reservationEndDate;

    private int guests;

    @ManyToMany(targetEntity = Utility.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Utility> utilities;

    private boolean isUsed = false;

    private double cancelingPercentage;

}