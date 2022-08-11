package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "select * from reservation r where r.client_id = ?1", nativeQuery = true)
    Collection<Reservation> findAllForClient(Long clientId);
}
