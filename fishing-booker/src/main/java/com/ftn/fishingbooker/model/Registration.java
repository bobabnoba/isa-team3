package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class Registration {
    @OneToOne
    private int userId;
    private RegistrationType type;
    private String motivation;
    private boolean isApproved;
    private String adminResponse;
}
