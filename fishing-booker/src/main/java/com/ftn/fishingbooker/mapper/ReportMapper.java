package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.NewReportDto;
import com.ftn.fishingbooker.model.AdventureReservationReport;
import com.ftn.fishingbooker.model.Instructor;

public class ReportMapper {

    public static AdventureReservationReport toAdventureEntity(NewReportDto report, Instructor instructor) {
        AdventureReservationReport newReport = new AdventureReservationReport();
        newReport.setComment(report.getComment());
        newReport.setClientShowedUp(report.isClientShowedUp());
        newReport.setAdminReviewed(false);
        newReport.setInstructor(instructor);
        newReport.setClientEmail(report.getClientEmail());
        return newReport;
    }

}
