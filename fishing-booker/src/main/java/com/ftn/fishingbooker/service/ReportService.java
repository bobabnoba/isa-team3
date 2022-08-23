package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.AdventureReservationReport;
import com.ftn.fishingbooker.model.Report;
import com.ftn.fishingbooker.projection.DatabaseReport;

import java.util.Collection;

public interface ReportService {

    Report create(AdventureReservationReport report, Long reservationId);

    Collection<DatabaseReport> getAllUnreviewedReports();
}
