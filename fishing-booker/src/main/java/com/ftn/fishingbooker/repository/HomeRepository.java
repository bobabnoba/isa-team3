package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.Collection;

public interface HomeRepository extends JpaRepository<VacationHome, Long> {

    @Query(value = "select * from vacation_home u where u.deleted = false", nativeQuery = true)
    Collection<VacationHome> getAll();

    @Query(value = "select a.*\n" +
            "from vacation_home as a\n" +
            "join vacation_home_reservations as ar on a.id = ar.vacation_home_id\n" +
            "where ar.reservations_id = ?1  ", nativeQuery = true)
    VacationHome getVacationHomeForReservation(Long reservationId);

    @Query("SELECT a FROM VacationHome a WHERE a.homeOwner.id = ?1 and a.deleted = false")
    Collection<VacationHome> findAllByOwnerId(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    VacationHome findLockedById(Long id);
}
