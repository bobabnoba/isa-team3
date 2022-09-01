package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dao.AdminReportResponse;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.dao.DatabaseReport;
import com.ftn.fishingbooker.repository.ReportRepository;
import com.ftn.fishingbooker.service.EmailService;
import com.ftn.fishingbooker.service.ReportService;
import com.ftn.fishingbooker.service.ReservationService;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Collection;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReservationService reservationService;
    private final EmailService emailService;


    public ReportServiceImpl(ReportRepository reportRepository, ReservationService reservationService, EmailService emailService) {
        this.reportRepository = reportRepository;
        this.reservationService = reservationService;
        this.emailService = emailService;
    }

    @Override
    public Report create(AdventureReservationReport report, Long reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        report.setClientEmail(reservation.getClient().getEmail());
        Report saved = reportRepository.save(report);
        reservation.setReport(saved);
        reservationService.save(reservation);
        return saved;
    }

    @Override
    public Collection<DatabaseReport> getAllUnreviewedReports() {
        return reportRepository.getUnprocessed();
    }

    @Override
    public void processAdminReview(AdminReportResponse response) throws MessagingException {
        Report report = reportRepository.findById(response.getReportId()).orElseThrow(() -> new ResourceConflictException("Report not found"));

        report.setAdminReviewed(true);
        reportRepository.save(report);

        String html = emailService.createAdminReportResponseEmail(response.getResponse(), response.addPenalty());
        emailService.sendEmail(response.getOwnerEmail(), "Your reservation report has been reviewed!", html);
        emailService.sendEmail(response.getClientEmail(), "Report from your reservation has been reviewed!", html);
    }

    @Override
    public Report create(BoatReservationReport report, Long reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        report.setClientEmail(reservation.getClient().getEmail());
        Report saved = reportRepository.save(report);
        reservation.setReport(saved);
        reservationService.save(reservation);
        return saved;
    }

    @Override
    public Report create(VacationHomeReservationReport report, Long reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        report.setClientEmail(reservation.getClient().getEmail());
        Report saved = reportRepository.save(report);
        reservation.setReport(saved);
        reservationService.save(reservation);
        return saved;
    }
}
