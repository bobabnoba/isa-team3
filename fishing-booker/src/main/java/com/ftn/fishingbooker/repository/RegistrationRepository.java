package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Registration;
import com.ftn.fishingbooker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
