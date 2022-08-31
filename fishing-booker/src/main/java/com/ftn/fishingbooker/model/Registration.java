package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    private String userEmail;

    public Registration(RegistrationType type, String motivation, String userEmail) {
        this.type = type;
        this.motivation = motivation;
        this.userEmail = userEmail;
        this.isApproved = false;
    }

}
