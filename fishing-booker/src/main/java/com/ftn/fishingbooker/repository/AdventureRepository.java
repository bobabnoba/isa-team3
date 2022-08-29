package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AdventureRepository extends JpaRepository<Adventure, Long> {

    @Query("SELECT a FROM Adventure a JOIN FETCH a.instructor i WHERE a.deleted = false")
    Collection<Adventure> findAllWithInstructor();

    @Query("SELECT a FROM Adventure a WHERE a.instructor.id = ?1 and a.deleted = false")
    Collection<Adventure> findAllByInstructorId(Long id);

    @Query("SELECT a.id FROM Adventure a WHERE a.instructor.id = ?1 and a.deleted = false")
    Collection<Long> getAllIdsByInstructorId(Long id);

    @Query("SELECT a FROM Adventure a JOIN FETCH a.reservations WHERE a.id = ?1 AND a.deleted = false")
    Adventure getWithReservations(Long id);

    @Query(value = "select a.*\n" +
            "   from adventure as a\n" +
            "   join adventure_reservations as ar on a.id = ar.adventure_id \n" +
            "   where ar.reservations_id = ?1  ", nativeQuery = true)
    Adventure getAdventureForReservation(Long reservationId);

}
