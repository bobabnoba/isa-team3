package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.ClientType;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Client extends User {

    private float points;

    @Enumerated(EnumType.STRING)
    private ClientType type;

    @OneToMany(mappedBy="client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservationsMade;
}
