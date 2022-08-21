package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AdventureRepository extends JpaRepository<Adventure, Long> {

    @Query("SELECT a FROM Adventure a JOIN FETCH a.instructor i WHERE a.deleted = false")
    Collection<Adventure> findAllWithInstructor();

    @Query("SELECT a FROM Adventure a WHERE a.instructor.id = ?1 and a.deleted = false")
    Collection<Adventure> findAllByInstructorId(Long id);

    @Query("SELECT a.id FROM Adventure a WHERE a.instructor.id = ?1 and a.deleted = false")
    Collection<Long> getAllIdsByInstructorId(Long id);
}
