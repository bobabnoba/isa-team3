package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.*;

public interface BoatAvailabilityService {
    BoatAvailability save(BoatAvailability boatAvailability);

    void delete(Long id);
}
