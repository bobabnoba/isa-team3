package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dao.AdminReportResponse;
import com.ftn.fishingbooker.model.AdventureReservationReport;
import com.ftn.fishingbooker.model.Report;
import com.ftn.fishingbooker.dao.DatabaseReport;

import javax.mail.MessagingException;
import java.util.Collection;

public interface ReportService {

    Report create(AdventureReservationReport report, Long reservationId);

    Collection<DatabaseReport> getAllUnreviewedReports();

    void processAdminReview(AdminReportResponse response) throws MessagingException;
}
