package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.*;

public interface BoatOwnerService {
    User registerBoatOwner(BoatOwner mapToBoatOwner, String motivation);
}
