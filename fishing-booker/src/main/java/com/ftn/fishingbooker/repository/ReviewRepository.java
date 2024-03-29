package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.enumeration.ReviewStatus;
import com.ftn.fishingbooker.model.ClientReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ReviewRepository extends JpaRepository<ClientReview, Long> {

    @Query(value = "select * from client_review c where c.reservation_id = ?1", nativeQuery = true)
    ClientReview findByReservationId(Long reservationId);

    Collection<ClientReview> findAllByStatus(ReviewStatus status);

    @Query(value = " select avg(r.rentalRating) " +
            "        from ClientReview r " +
            "        where r.rentalId = :rentalId and r.status = com.ftn.fishingbooker.enumeration.ReviewStatus.APPROVED")
    double calculateRentalRating(Long rentalId);

    @Query(value = " select avg(r.ownerRating) " +
            "        from ClientReview r " +
            "        where r.ownerEmail = :email and r.status = com.ftn.fishingbooker.enumeration.ReviewStatus.APPROVED")
    double calculateOwnerRating(String email);
}
