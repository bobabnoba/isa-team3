package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.NewReportDto;
import com.ftn.fishingbooker.dto.ReportDto;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.ReportMapper;
import com.ftn.fishingbooker.model.AdventureReservationReport;
import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.Report;
import com.ftn.fishingbooker.projection.DatabaseReport;
import com.ftn.fishingbooker.service.InstructorService;
import com.ftn.fishingbooker.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/reservation-reports")
public class ReservationReportController {

    private final ReportService reportService;
    private final InstructorService instructorService;

    public ReservationReportController(ReportService reportService, InstructorService instructorService) {
        this.reportService = reportService;
        this.instructorService = instructorService;
    }

    @GetMapping("/unprocessed")
    public ResponseEntity<Collection<DatabaseReport>> getAllUnreviewedReports() {
        return ResponseEntity.ok(reportService.getAllUnreviewedReports());
    }

    @PostMapping("/{reservationId}")
    public ResponseEntity<Report> addReport(@PathVariable Long reservationId, @RequestBody NewReportDto report) {
        if(report.getType() == ReservationType.ADVENTURE){
            Instructor instructor = instructorService.findByEmail(report.getOwnerEmail());
            AdventureReservationReport newReport = ReportMapper.toAdventureEntity(report, instructor);
            return ResponseEntity.ok(reportService.create(newReport, reservationId));
        }
        return ResponseEntity.badRequest().build();
    }
}
