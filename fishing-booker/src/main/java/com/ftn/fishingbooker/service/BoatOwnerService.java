package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;

import java.util.*;

public interface BoatOwnerService {

    User register(BoatOwner boatOwner, String motivation) throws ResourceConflictException;

    void updatePoints(BoatOwner owner, double reservationPrice);

    BoatOwner getWithAvailability(String email);

    boolean checkAvailability(Date from, Date to, String boatOwnerEmail);
}
