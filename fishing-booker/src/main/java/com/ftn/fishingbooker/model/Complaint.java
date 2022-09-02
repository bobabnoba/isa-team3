package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.ComplaintStatus;
import com.ftn.fishingbooker.enumeration.RentalType;
import com.ftn.fishingbooker.enumeration.ReservationType;
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
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String complaint;

    @Column(unique = true)
    private Long reservationId;

    @Enumerated(EnumType.STRING)
    RentalType rentalType;

    private String ownerEmail;

    private Long rentalId;

    private String clientEmail;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status = ComplaintStatus.PENDING;

    private String adminResponse;

    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;


}
