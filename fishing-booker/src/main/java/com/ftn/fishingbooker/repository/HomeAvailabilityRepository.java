package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.*;
import org.springframework.data.jpa.repository.*;

public interface HomeAvailabilityRepository extends JpaRepository<VacationHomeAvailability, Long> {
}
