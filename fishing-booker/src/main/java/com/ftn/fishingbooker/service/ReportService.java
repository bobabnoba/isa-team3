package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dao.AdminReportResponse;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.dao.DatabaseReport;

import javax.mail.MessagingException;
import java.util.Collection;

public interface ReportService {

    Report create(AdventureReservationReport report, Long reservationId);

    Collection<DatabaseReport> getAllUnreviewedReports();

    void processAdminReview(AdminReportResponse response) throws MessagingException;

    Report create(BoatReservationReport report, Long reservationId);

    Report create(VacationHomeReservationReport report, Long reservationId);
}
