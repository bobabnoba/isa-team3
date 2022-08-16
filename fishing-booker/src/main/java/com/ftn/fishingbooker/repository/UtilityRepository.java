package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Utility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UtilityRepository extends JpaRepository<Utility, Long> {
    Utility findByName(String name);
}
