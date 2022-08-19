package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.UserRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRankRepository extends JpaRepository<UserRank, Long> {
}
