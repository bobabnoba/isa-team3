package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.*;

public interface BoatOwnerAvailabilityService {
    BoatOwnerAvailability save(BoatOwnerAvailability boatOwnerAvailability);

    void delete(Long id);
}
