package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Report;
import com.ftn.fishingbooker.dao.DatabaseReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ReportRepository extends JpaRepository<Report, Long> {


    @Query(value = "SELECT r.id, r.comment, r.client_showed_up as clientShowedUp, " +
            " r.instructor_id as instructorId, r.boat_owner_id as boatOwnerId, " +
            " r.home_owner_id as homeOwnerId, r.dtype as type, r.client_email as clientEmail," +
            "r.penalty_suggested as penaltySuggested " +
            " FROM reports r WHERE r.admin_reviewed = false", nativeQuery = true)
    Collection<DatabaseReport> getUnprocessed();

}
