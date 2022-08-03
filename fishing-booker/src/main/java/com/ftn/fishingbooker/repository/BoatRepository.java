package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.boat.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface BoatRepository extends JpaRepository<Boat, Long> {

    @Query(value = "select * from boat u where u.deleted = false", nativeQuery = true)
    ArrayList<Boat> getAll();
}
