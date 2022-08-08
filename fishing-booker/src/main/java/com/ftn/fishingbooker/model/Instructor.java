package com.ftn.fishingbooker.model;

import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Instructor extends User {

    private double rating = 0.0;

    //    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @ToString.Exclude
//    private Set<Adventure> adventures;
//
//    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private Set<Report> reservationReport;
//
//    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private Set<Complaint> complaints;

}
