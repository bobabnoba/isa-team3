package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.enumeration.ReviewStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ClientReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long reservationId;

    private String review;

    private Double ownerRating;

    private Double rentalRating;

    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    // More that needs

    private String ownerEmail;

    private Long rentalId;

    private String rentalName;

    private String clientEmail;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status = ReviewStatus.PENDING;






}
