package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.*;
import org.springframework.data.jpa.repository.*;

public interface HomeOwnerRepository extends JpaRepository<HomeOwner, Long> {
    HomeOwner findByEmail(String email);
}
