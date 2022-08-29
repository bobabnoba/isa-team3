package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.*;

import java.util.*;
import java.util.stream.*;

public interface BoatAvailabilityService {
    BoatAvailability save(BoatAvailability boatAvailability);

    void delete(Long id);

    void delete(Set<BoatAvailability> availabilityStream);
}
