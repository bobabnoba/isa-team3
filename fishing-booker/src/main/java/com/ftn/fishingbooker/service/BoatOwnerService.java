package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dao.*;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;

import java.util.*;

public interface BoatOwnerService {

    User register(BoatOwner boatOwner, String motivation) throws ResourceConflictException;

    void updatePoints(BoatOwner owner, double reservationPrice);

    BoatOwner getWithAvailability(String email);

    boolean checkAvailability(Date from, Date to, String boatOwnerEmail);

    void updateAvailability(Date reservationStartDate, Date reservationEndDate, String ownerEmail);

//    Collection<ReservationInfo> getUpcomingReservationsForBoatOwner(String email);
//
//    Collection<Reservation> getPastReservationsForBoatOwner(String email);
//
//    Collection<Reservation> getCurrentReservationsForBoatOwner(String email);

    BoatOwner getByEmail(String boatOwnerEmail);
}
