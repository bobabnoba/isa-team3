package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.ClientReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ClientReview, Long> {
}
