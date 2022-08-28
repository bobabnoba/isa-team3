package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Earnings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;

public interface EarningsRepository extends JpaRepository<Earnings, Long> {

    Earnings getByAdvertiserEmail(String email);

    @Query(value = "select e " +
            "       from Earnings e " +
            "       where e.transactionDate >= :from and e.transactionDate <= :to and e.goesToApp = true ")
    Collection<Earnings> getAllInDateRange(@Param("from") Date from,
                                                   @Param("to") Date to);

    @Query(value = "select e " +
            "       from Earnings e " +
            "       where e.advertiserEmail = :email and " +
            "       e.transactionDate >= :from and e.transactionDate <= :to and e.goesToApp = false ")
    Collection<Earnings> getAdvertisersInDateRange(@Param("from") Date from,
                                                   @Param("to") Date to,
                                                   @Param("email") String email);

    @Query(value = "select e " +
            "       from Earnings e " +
            "       where e.reservation.id = :id ")
    Collection<Earnings> getByReservationId(@Param("id") Long reservationId);
}
