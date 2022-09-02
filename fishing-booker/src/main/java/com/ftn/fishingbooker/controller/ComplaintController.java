package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.ComplaintDto;
import com.ftn.fishingbooker.enumeration.ComplaintStatus;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.ComplaintMapper;
import com.ftn.fishingbooker.model.Complaint;
import com.ftn.fishingbooker.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complaints")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @GetMapping("/reservation/{reservationId}")
    public Boolean checkForComplaint(@PathVariable Long reservationId) {

        return complaintService.checkForComplaint(reservationId);
    }

    @PostMapping("/{clientEmail}")
    public ResponseEntity CreateComplaint(@PathVariable String clientEmail, @RequestBody ComplaintDto complaintDto) {

        Complaint complaint = ComplaintMapper.map(complaintDto);
        complaint.setClientEmail(clientEmail);
        complaint.setStatus(ComplaintStatus.PENDING);

        if (complaintDto.getReservationType() == ReservationType.ADVENTURE) {

            return ResponseEntity.ok(complaintService.makeAdventureComplaint(complaint));

        } else if (complaintDto.getReservationType() == ReservationType.BOAT) {
            return ResponseEntity.ok(complaintService.makeBoatComplaint(complaint));


        } else if (complaintDto.getReservationType() == ReservationType.VACATION_HOME) {
            return ResponseEntity.ok(complaintService.makeVacationHomeComplaint(complaint));

        }

        return ResponseEntity.badRequest().build();
    }
}
