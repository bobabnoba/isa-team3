package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AdventureRepository extends JpaRepository<Adventure, Long> {

    @Query("SELECT a FROM Adventure a JOIN FETCH a.instructor i")
    Collection<Adventure> findAllWithInstructor();

    @Query("SELECT a FROM Adventure a WHERE a.instructor.id = ?1")
    Collection<Adventure> findAllByInstructorId(Long id);
}
