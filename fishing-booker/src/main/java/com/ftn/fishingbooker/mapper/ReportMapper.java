package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dao.AdminReportResponse;
import com.ftn.fishingbooker.dto.AdminReportResponseDto;
import com.ftn.fishingbooker.dto.NewReportDto;
import com.ftn.fishingbooker.dto.ReportDto;
import com.ftn.fishingbooker.model.*;

public class ReportMapper {

    public static AdventureReservationReport toAdventureEntity(NewReportDto report, Instructor instructor) {
        AdventureReservationReport newReport = new AdventureReservationReport();
        newReport.setComment(report.getComment());
        newReport.setClientShowedUp(report.isClientShowedUp());
        newReport.setAdminReviewed(false);
        newReport.setInstructor(instructor);
        newReport.setClientEmail(report.getClientEmail());
        newReport.setPenaltySuggested(report.isPenaltySuggested());
        return newReport;
    }

    public static AdminReportResponse toDao(AdminReportResponseDto dto) {
        AdminReportResponse response = new AdminReportResponse();
        response.setReportId(dto.getReportId());
        response.setResponse(dto.getResponse());
        response.setOwnerEmail(dto.getOwnerEmail());
        response.setClientEmail(dto.getClientEmail());
        response.setAddPenalty(dto.isPenalty());
        return response;
    }

    public static ReportDto toDto(Report report) {
        ReportDto dto = new ReportDto();
        dto.setId(report.getId());
        dto.setComment(report.getComment());
        dto.setClientShowedUp(report.isClientShowedUp());
        dto.setClientEmail(report.getClientEmail());
        dto.setPenaltySuggested(report.isPenaltySuggested());
        return dto;
    }

    public static BoatReservationReport toBoatEntity(NewReportDto report, BoatOwner owner) {
        BoatReservationReport newReport = new BoatReservationReport();
        newReport.setComment(report.getComment());
        newReport.setClientShowedUp(report.isClientShowedUp());
        newReport.setAdminReviewed(false);
        newReport.setBoatOwner(owner);
        newReport.setClientEmail(report.getClientEmail());
        newReport.setPenaltySuggested(report.isPenaltySuggested());
        return newReport;
    }

    public static VacationHomeReservationReport toHomeEntity(NewReportDto report, HomeOwner owner) {
        VacationHomeReservationReport newReport = new VacationHomeReservationReport();
        newReport.setComment(report.getComment());
        newReport.setClientShowedUp(report.isClientShowedUp());
        newReport.setAdminReviewed(false);
        newReport.setHomeOwner(owner);
        newReport.setClientEmail(report.getClientEmail());
        newReport.setPenaltySuggested(report.isPenaltySuggested());
        return newReport;
    }
}
