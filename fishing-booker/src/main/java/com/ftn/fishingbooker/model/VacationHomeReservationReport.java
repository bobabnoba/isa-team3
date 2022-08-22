package com.ftn.fishingbooker.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VacationHomeReservationReport extends Report {

    @ManyToOne(targetEntity = HomeOwner.class)
    private HomeOwner homeOwner;

}
