package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Instructor findByEmail(String email);
    
}
