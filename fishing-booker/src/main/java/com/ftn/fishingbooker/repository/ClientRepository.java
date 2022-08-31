package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    @Query(value = "select exists(select *\n" +
            "from vacation_home_subscription as vhs \n" +
            "where client_id = ?1 and vacation_home_id = ?2);", nativeQuery = true)
    boolean vacationHomeSubscriptionExists(Long clientId, Long entityId);

    @Query(value = "select exists(select *\n" +
            "from boat_subscription as vhs \n" +
            "where client_id = ?1 and boat_id = ?2);", nativeQuery = true)
    boolean boatSubscriptionExists(Long clientId, Long entityId);

    @Query(value = "select exists(select *\n" +
            "from instructor_subscription as vhs \n" +
            "where client_id = ?1 and instructor_id = ?2);", nativeQuery = true)
    boolean instructorHomeSubscriptionExists(Long clientId, Long entityId);
}
