package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String city;

    private String street;

    private Long zipCode;

}
