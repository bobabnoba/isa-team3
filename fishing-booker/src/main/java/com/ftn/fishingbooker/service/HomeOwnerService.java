package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;

public interface HomeOwnerService {

    User register(HomeOwner homeOwner, String motivation) throws ResourceConflictException;

    void updatePoints(HomeOwner owner, double reservationPrice);

}
