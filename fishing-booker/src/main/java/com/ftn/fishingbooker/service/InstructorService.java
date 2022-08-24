package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.dao.ReservationInfo;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.Date;

public interface InstructorService {

    User register(Instructor instructor, String motivation) throws MessagingException;

    InstructorAvailability addAvailabilityPeriod(InstructorAvailability availability, String email);

    Instructor getWithAvailability(String email);

    Collection<Instructor> getAll();

    boolean checkAvailability(Date from, Date to, String instructorEmail);

    void updateAvailability(InstructorAvailability periodToDelete, String email);

    void makeReservation(Long adventureId, Reservation reservation);

    void updatePoints(Instructor instructor, double reservationPrice);

    Collection<ReservationInfo> getUpcomingReservationsForInstructor(String email);

    Collection<Reservation> getPastReservationsForInstructor(String email);

    Instructor findByEmail(String email);

    Instructor findById(Long instructorId);

    boolean hasOverlappingReservation(String email, Date from, Date to);
}
