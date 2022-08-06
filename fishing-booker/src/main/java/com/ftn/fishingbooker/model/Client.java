package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.ClientType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Client extends User {

    private float points;

    @Enumerated(EnumType.STRING)
    private ClientType type;

    @OneToMany(targetEntity = Reservation.class, mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Reservation> reservationsMade;


}
