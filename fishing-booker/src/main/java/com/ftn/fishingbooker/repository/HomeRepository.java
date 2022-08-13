package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.VacationHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface HomeRepository extends JpaRepository<VacationHome, Long> {
    @Query(value = "select * from vacation_home u where u.deleted = false", nativeQuery = true)
    Collection<VacationHome> getAll();

}
