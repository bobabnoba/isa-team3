package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.ClientType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Client extends User {

    private float points;

    @Enumerated(EnumType.STRING)
    private ClientType type;

    @OneToMany(mappedBy="client")
    private List<Reservation> reservationsMade;
}
