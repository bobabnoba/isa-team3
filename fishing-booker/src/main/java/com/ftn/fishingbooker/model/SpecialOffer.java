package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.ReservationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

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

    private Date availableFrom;

    private Date availableTo;

    @Enumerated(EnumType.STRING)
    private ReservationType type;

    private Date reservationStartDate;

    private Date reservationEndDate;

    private int guests;

    private boolean isUsed = false;

}