package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.VacationHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface HomeRepository extends JpaRepository<VacationHome, Long> {

    @Query(value = "select * from vacation_home u where u.deleted = false", nativeQuery = true)
    Collection<VacationHome> getAll();

    @Query(value = "select a.*\n" +
            "from vacation_home as a\n" +
            "join vacation_home_reservations as ar on a.id = ar.vacation_home_id\n" +
            "where ar.reservations_id = ?1  ", nativeQuery = true)
    VacationHome getVacationHomeForReservation(Long reservationId);

}
