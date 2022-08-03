package com.ftn.fishingbooker.model.adventure;

import com.ftn.fishingbooker.model.Reservation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AdventureReservation extends Reservation {
    private String adventureDestination;

    @ManyToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;

}
