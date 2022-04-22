package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface DeleteAccountRequestRepository extends JpaRepository<DeleteAccountRequest,Long> {

    DeleteAccountRequest findByUserEmail(String userEmail);

    @Query(value = "select * from delete_account_request u where u.user_email = ?1", nativeQuery = true)
    Iterable<DeleteAccountRequest> findAllByUserEmail(String userEmail);
}
