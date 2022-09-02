package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.enumeration.ComplaintStatus;
import com.ftn.fishingbooker.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query(value = "select * from complaint c where c.reservation_id = ?1", nativeQuery = true)
    Complaint findByReservationId(Long reservationId);

    Collection<Complaint> findAllByStatus(ComplaintStatus status);
}
