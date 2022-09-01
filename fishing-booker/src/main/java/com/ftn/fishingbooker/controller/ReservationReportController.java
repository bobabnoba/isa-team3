package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.AdminReportResponseDto;
import com.ftn.fishingbooker.dto.NewReportDto;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.ReportMapper;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.dao.DatabaseReport;
import com.ftn.fishingbooker.service.*;
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
    private final BoatOwnerService boatOwnerService;
    private final HomeOwnerService homeOwnerService;

    public ReservationReportController(ReportService reportService, InstructorService instructorService,
                                       ClientService clientService, BoatOwnerService boatOwnerService,
                                       HomeOwnerService homeOwnerService) {
        this.reportService = reportService;
        this.instructorService = instructorService;
        this.clientService = clientService;
        this.boatOwnerService = boatOwnerService;
        this.homeOwnerService = homeOwnerService;
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
        }else if(report.getType() == ReservationType.BOAT){
            BoatOwner owner = boatOwnerService.getByEmail(report.getOwnerEmail());
            BoatReservationReport newReport = ReportMapper.toBoatEntity(report, owner);
            return ResponseEntity.ok(reportService.create(newReport, reservationId));
        }else if(report.getType() == ReservationType.VACATION_HOME){
            HomeOwner owner = homeOwnerService.getByEmail(report.getOwnerEmail());
            VacationHomeReservationReport newReport = ReportMapper.toHomeEntity(report, owner);
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
