package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;

public interface BoatRepository extends JpaRepository<Boat, Long> {

    @Query(value = "select * from boat u where u.deleted = false", nativeQuery = true)
    Collection<Boat> getAll();

    @Query("SELECT a FROM Boat a WHERE a.boatOwner.id = ?1 and a.deleted = false")
    Collection<Boat> findAllByOwnerId(Long id);

    @Query(value = "select a.*\n" +
            "   from boat as a\n" +
            "   join boat_reservations as ar on a.id = ar.boat_id \n" +
            "   where ar.reservations_id = ?1  ", nativeQuery = true)
    Boat getBoatForReservation(Long reservationId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    Boat findLockedById(Long boatId);
}
