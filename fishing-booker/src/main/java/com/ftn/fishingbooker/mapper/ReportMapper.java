package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dao.AdminReportResponse;
import com.ftn.fishingbooker.dto.AdminReportResponseDto;
import com.ftn.fishingbooker.dto.NewReportDto;
import com.ftn.fishingbooker.dto.ReportDto;
import com.ftn.fishingbooker.model.AdventureReservationReport;
import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.Report;

import java.util.Set;

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
}
