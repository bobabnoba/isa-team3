package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;


public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Instructor findByEmail(String email);

    @Query(value = "select * from instructor u where u.is_deleted = false and u.is_blocked = false and u.is_activated = true ", nativeQuery = true)
    Collection<Instructor> getAll();

    @Query(value = "select * from instructor u where u.is_deleted = false and u.is_blocked = false and u.is_activated = true ", nativeQuery = true)
    Collection<Reservation> getReservationsForInstructor(Long id);
}
