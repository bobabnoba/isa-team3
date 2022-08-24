package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;

public interface BoatOwnerService {

    User register(BoatOwner boatOwner, String motivation) throws ResourceConflictException;

    void updatePoints(BoatOwner owner, double reservationPrice);

    BoatOwner getWithAvailability(String email);
}
