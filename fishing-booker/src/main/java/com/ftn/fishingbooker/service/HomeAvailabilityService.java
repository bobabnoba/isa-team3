package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.*;

import java.util.*;

public interface HomeAvailabilityService {
    VacationHomeAvailability save(VacationHomeAvailability homeAvailability);

    void delete(Long id);

    void delete(Set<VacationHomeAvailability> availabilitySet);
}
