package com.ftn.fishingbooker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ToString.Exclude
    @JsonIgnore
    private Set<Reservation> reservationsMade;

    private int noOfPenalties;

    @ManyToMany(targetEntity = Boat.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "boat_subscription",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "boat_id"))
    private Set<Boat> boatSubscription;

    @ManyToMany(targetEntity = VacationHome.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "vacation_home_subscription",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "vacation_home_id"))
    private Set<VacationHome> vacationHomeSubscription;

    @ManyToMany(targetEntity = Instructor.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "instructor_subscription",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private Set<Instructor> instructorSubscription;

}
