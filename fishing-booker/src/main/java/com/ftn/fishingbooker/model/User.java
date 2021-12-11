package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ApplicationUser")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String address;

    private String phone;

    private String city;

    private String country;

    private boolean isActivated;

    private boolean isBlocked;

    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

}
