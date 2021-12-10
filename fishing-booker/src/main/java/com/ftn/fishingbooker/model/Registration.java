package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Registration {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RegistrationType type;

    private String motivation;

    private boolean isApproved;

    private String adminResponse;

    @OneToOne(mappedBy="registration", fetch = FetchType.LAZY)
    private User registeredUsers;


}
