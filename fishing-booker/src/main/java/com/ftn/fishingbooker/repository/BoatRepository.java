package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface BoatRepository extends JpaRepository<Boat, Long> {
    @Query(value = "select * from boat u where u.deleted = false", nativeQuery = true)
    Collection<Boat> getAll();
}
