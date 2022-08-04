package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.DeleteAccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface DeleteAccountRepository extends JpaRepository<DeleteAccountRequest, Long> {

    @Query("SELECT d FROM DeleteAccountRequest d WHERE d.isApproved = false AND d.adminResponse IS NULL")
    Collection<DeleteAccountRequest> getAllUnprocessed();
}
