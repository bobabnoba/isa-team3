package com.ftn.fishingbooker.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int minPoints;

    // use it for calculating points gained by the reservation made
    // defines number of points user gets of each payment ( i.e. gainedPoints = 80% of the price)
    private double reservationPercentage;

    // use it for calculating price of the reservation --- clients
    // use it for calculating income of the advertiser --- advertisers
    private double percentage;
}
