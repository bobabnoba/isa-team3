package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.*;

import java.util.*;

public interface BoatOwnerAvailabilityService {
    BoatOwnerAvailability save(BoatOwnerAvailability boatOwnerAvailability);

    void delete(Long id);

    void delete(Set<BoatOwnerAvailability> availabilityStream);
}
