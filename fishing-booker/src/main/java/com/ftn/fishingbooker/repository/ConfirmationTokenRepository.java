package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    @Query(value = "select * from confirmation_token c where c.token = ?1", nativeQuery = true)
    ConfirmationToken findByToken(String token);
}