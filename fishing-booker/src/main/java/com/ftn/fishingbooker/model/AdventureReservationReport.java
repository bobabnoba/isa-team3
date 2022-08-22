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
public class AdventureReservationReport extends Report {

    @ManyToOne(targetEntity = Instructor.class, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Instructor instructor;
}
