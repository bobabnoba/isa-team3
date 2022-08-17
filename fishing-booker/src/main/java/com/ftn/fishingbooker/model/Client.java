package com.ftn.fishingbooker.model;

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

    @OneToMany(targetEntity = Reservation.class, mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Reservation> reservationsMade;


}
