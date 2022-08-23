package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.AdminReportResponseDto;
import com.ftn.fishingbooker.dto.NewReportDto;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.ReportMapper;
import com.ftn.fishingbooker.model.AdventureReservationReport;
import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.Report;
import com.ftn.fishingbooker.dao.DatabaseReport;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.InstructorService;
import com.ftn.fishingbooker.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Collection;

@RestController
@RequestMapping("/reservation-reports")
public class ReservationReportController {

    private final ReportService reportService;
    private final InstructorService instructorService;
    private final ClientService clientService;

    public ReservationReportController(ReportService reportService, InstructorService instructorService, ClientService clientService) {
        this.reportService = reportService;
        this.instructorService = instructorService;
        this.clientService = clientService;
    }

    @GetMapping("/unprocessed")
    public ResponseEntity<Collection<DatabaseReport>> getAllUnreviewedReports() {
        Collection<DatabaseReport> reports = reportService.getAllUnreviewedReports();
        return ResponseEntity.ok(reports);
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

    @PostMapping("/admin-review")
    public ResponseEntity<Object> adminReview(@RequestBody AdminReportResponseDto report)  {
        if(report.isPenalty()) {
            clientService.addPenalty(report.getClientEmail());
        }
        try{
            reportService.processAdminReview(ReportMapper.toDao(report));
        } catch (MessagingException e) {
            return ResponseEntity.internalServerError().body("Error while sending email!");
        }

        return ResponseEntity.ok().build();
    }
}
