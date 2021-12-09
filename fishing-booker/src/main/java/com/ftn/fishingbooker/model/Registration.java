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
    private Long id;

    @Enumerated(EnumType.STRING)
    private RegistrationType type;

    private String motivation;

    private boolean isApproved;

    private String adminResponse;

    @OneToMany(mappedBy="registration")
    private Set<User> registeredUsers;


}
