package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.home.VacationHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Repository
public interface VacationHomeRepository extends JpaRepository<VacationHome, Long> {

    @Query(value = "select * from vacation_home u where u.deleted = false", nativeQuery = true)
    ArrayList<VacationHome> getAll();

}
