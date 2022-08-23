package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.AdventureReservationReport;
import com.ftn.fishingbooker.model.Report;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.projection.DatabaseReport;
import com.ftn.fishingbooker.repository.ReportRepository;
import com.ftn.fishingbooker.service.ReportService;
import com.ftn.fishingbooker.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReservationService reservationService;

    public ReportServiceImpl(ReportRepository reportRepository, ReservationService reservationService) {
        this.reportRepository = reportRepository;
        this.reservationService = reservationService;
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
}
