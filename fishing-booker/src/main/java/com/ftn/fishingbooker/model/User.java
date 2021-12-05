package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class User {
    @Id
    private int id;
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
    private int registrationId;
}
