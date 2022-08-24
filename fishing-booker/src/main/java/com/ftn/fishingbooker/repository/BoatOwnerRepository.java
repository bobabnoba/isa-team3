package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.*;
import org.springframework.data.jpa.repository.*;

public interface BoatOwnerRepository extends JpaRepository<BoatOwner, Long> {
    BoatOwner findByEmail(String email);
}
