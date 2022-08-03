package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.adventure.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface AdventureRepository extends JpaRepository<Adventure, Long> {

    @Query(value = "select * from adventure u where u.deleted = false", nativeQuery = true)
    ArrayList<Adventure> getAll();
}
