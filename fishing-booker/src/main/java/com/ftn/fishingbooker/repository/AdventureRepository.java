package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureRepository extends JpaRepository<Adventure, Long> {
}
