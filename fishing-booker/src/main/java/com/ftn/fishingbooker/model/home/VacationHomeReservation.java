package com.ftn.fishingbooker.model.home;

import com.ftn.fishingbooker.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class VacationHomeReservation extends Reservation {

    @ManyToOne
    @JoinColumn(name = "home_id",nullable = false)
    private VacationHome vacationHome;

}
