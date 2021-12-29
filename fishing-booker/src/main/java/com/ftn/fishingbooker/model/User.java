package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ApplicationUser")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "mySeqGenV1", sequenceName = "myGenV1", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV1")
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

    private boolean testField1;

    private boolean testField2;

    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roles;

}
