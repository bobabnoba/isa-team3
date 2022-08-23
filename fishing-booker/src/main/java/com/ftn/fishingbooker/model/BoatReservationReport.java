package com.ftn.fishingbooker.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BoatReservationReport extends Report{

    @ManyToOne(targetEntity = BoatOwner.class, fetch = FetchType.LAZY)
    private BoatOwner boatOwner;

}
