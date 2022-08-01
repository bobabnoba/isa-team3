package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Registration;
import com.ftn.fishingbooker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query(value = "select * from registration r where r.is_approved = false and r.admin_response is null", nativeQuery = true)
    Collection<Registration> findUnprocessedRequests();

    @Query(value = "select * from registration r where r.user_email = ?1", nativeQuery = true)
    Registration findByUserEmail(String userEmail);
}
