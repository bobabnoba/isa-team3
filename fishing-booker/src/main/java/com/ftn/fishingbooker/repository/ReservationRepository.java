package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.dao.*;
import com.ftn.fishingbooker.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "select *\n" +
            "from reservation r\n" +
            "where r.client_id = ?1 and r.is_cancelled = false", nativeQuery = true)
    Collection<Reservation> findAllForClient(Long clientId);

    @Query(value = "SELECT r.*\n" +
            "    FROM vacation_home_reservations as vhr\n" +
            "    join reservation as r on r.id = vhr.reservations_id\n" +
            "    where vhr.vacation_home_id = ?1 and r.is_cancelled = false ", nativeQuery = true)
    Collection<Reservation> getReservationForVacationHome(Long homeId);

    @Query(value = "SELECT r.*\n" +
            "    FROM boat_reservations as vhr\n" +
            "    join reservation as r on r.id = vhr.reservations_id\n" +
            "    where vhr.boat_id = ?1 and r.is_cancelled = false ", nativeQuery = true)
    Collection<Reservation> getReservationForBoat(Long boatId);

    @Query(value = "SELECT r.*\n" +
            "    FROM adventure_reservations as vhr\n" +
            "    join reservation as r on r.id = vhr.reservations_id\n" +
            "    where vhr.adventure_id in ?1 and r.is_cancelled = false ", nativeQuery = true)
    Collection<Reservation> getReservationsForAdventures(Collection<Long> ids);

    @Query(value = "select r.id as id, r.startDate as startDate, a.durationInHours as durationInHours, " +
            "    r.price as price, r.guests as guests" +
            "    from Adventure a " +
            "    join a.reservations r " +
            "    where a.instructor.id = :id and r.isCancelled = false and r.startDate > current_date")
    Collection<ReservationInfo> getUpcomingReservationsForInstructor(@Param("id") Long id);

    @Query(value = "select r " +
            "    from Adventure a " +
            "    join a.reservations r " +
            "    left join r.report report " +
            "    where a.instructor.id = :id and r.isCancelled = false and r.startDate <= current_date")
    Collection<Reservation> getPastReservationsForInstructor(@Param("id") Long id);


    @Query(value = "select r.* " +
            "       from adventure as a " +
            "       join adventure_reservations as  ar on ar.adventure_id = a.id " +
            "       join reservation as r on ar.reservations_id = r.id " +
            "       where a.instructor_id = ?1 and r.start_date < NOW() and r.end_date > NOW()", nativeQuery = true)
    Reservation getOngoingReservationForInstructor(Long id);


    @Query(value = "select count(r.id) " +
            "    from Adventure a " +
            "    join a.reservations r " +
            "    where a.id = :id and r.isCancelled = false and r.startDate >= current_date")
    int noOfIncomingReservationsForAdventure(Long id);

    @Query(value = "select count(r.id) " +
            "    from Adventure a " +
            "    join a.reservations r " +
            "    where a.instructor.id = :id and r.isCancelled = false and r.startDate >= current_date")
    int noOfIncomingReservationsForInstructor(Long id);

    @Query(value = "select count(r.id) " +
            "    from Boat b " +
            "    join b.reservations r " +
            "    where b.id = :id and r.isCancelled = false and r.startDate >= current_date")
    int noOfIncomingReservationsForBoat(Long id);

    @Query(value = "select count(r.id) " +
            "    from Boat b " +
            "    join b.reservations r " +
            "    where b.boatOwner.id = :id and r.isCancelled = false and r.startDate >= current_date")
    int noOfIncomingReservationsForBoatOwner(Long id);

    @Query(value = "select count(r.id) " +
            "    from VacationHome h " +
            "    join h.reservations r " +
            "    where h.id = :id and r.isCancelled = false and r.startDate >= current_date")
    int noOfIncomingReservationsForHome(Long id);

    @Query(value = "select count(r.id) " +
            "    from VacationHome h " +
            "    join h.reservations r " +
            "    where h.homeOwner.id = :id and r.isCancelled = false and r.startDate >= current_date")
    int noOfIncomingReservationsForHomeOwner(Long id);


    @Query(value = "select count(r.id) " +
            "    from Reservation r " +
            "    where r.client.id = :id and r.isCancelled = false and r.startDate >= current_date")
    int noOfIncomingReservationsForClient(Long id);

    @Query(value = "select r.id as id, r.startDate as startDate, r.endDate as endDate, r.price as price, a.name as boatName" +
            "    from Boat a " +
            "    join a.reservations r " +
            "    where a.boatOwner.id = :id and r.isCancelled = false and r.startDate > current_date")
    Collection<BoatReservationInfo> getUpcomingReservationsForBoatOwner(@Param("id") Long id);

    @Query(value = "select r " +
            "    from Boat a " +
            "    join a.reservations r " +
            "    left join r.report report " +
            "    where a.boatOwner.id = :id and r.isCancelled = false and r.endDate < current_date")
    Collection<Reservation> getPastReservationsForBoatOwner(@Param("id") Long id);

    @Query(value = "select r.id as id, r.startDate as startDate, r.endDate as endDate, r.price as price, a.name as boatName " +
            "    from Boat a " +
            "    join a.reservations r " +
            "    where a.boatOwner.id = :id and r.isCancelled = false and r.startDate <= current_date and r.endDate >= current_date")
    Collection<BoatReservationInfo> getCurrentReservationsForBoatOwner(@Param("id") Long id);


    @Query( "    select r.startDate as startDate, r.endDate as endDate,  " +
            "    c.firstName as firstName, c.lastName as lastName, c.email as email, " +
            "    a.title as title " +
            "    from Adventure a " +
            "    join a.reservations r " +
            "    join r.client c " +
            "    where a.instructor.id = :id and r.isCancelled = false")
    Collection<ReservationCalendarInfo> getAllReservationForInstructor(Long id);
}
