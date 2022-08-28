package com.ftn.fishingbooker.model;

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
public class Earnings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Reservation.class, cascade = CascadeType.MERGE)
    private Reservation reservation;

    @Column(nullable = false)
    private String advertiserEmail;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private Date transactionDate;

    @Column(nullable = false)
    private boolean goesToApp;

    public Earnings(Reservation reservation, double amount, String email, boolean goesToApp) {
        this.reservation = reservation;
        this.advertiserEmail = email;
        this.amount = amount;
        this.goesToApp = goesToApp;
        this.transactionDate = new Date();
    }
}
