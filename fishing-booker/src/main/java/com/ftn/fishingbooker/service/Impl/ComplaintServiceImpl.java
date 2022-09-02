package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.model.Boat;
import com.ftn.fishingbooker.model.Complaint;
import com.ftn.fishingbooker.model.VacationHome;
import com.ftn.fishingbooker.repository.ComplaintRepository;
import com.ftn.fishingbooker.service.AdventureService;
import com.ftn.fishingbooker.service.BoatService;
import com.ftn.fishingbooker.service.ComplaintService;
import com.ftn.fishingbooker.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final AdventureService adventureService;
    private final UserServiceImpl userService;
    private final BoatService boatService;
    private final HomeService homeService;

    @Override
    public Boolean checkForComplaint(Long reservationId) {
        Complaint complaint = complaintRepository.findByReservationId(reservationId);
        if (complaint != null) {
            return true;
        }
        return false;
    }

    @Override
    public Complaint makeAdventureComplaint(Complaint complaint) {
        Adventure adventure = adventureService.getAdventureForReservation(complaint.getReservationId());
        complaint.setRentalId(adventure.getId());
        String ownerEmail = userService.getUserById(adventure.getInstructor().getId()).getEmail();
        complaint.setOwnerEmail(ownerEmail);

        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint makeBoatComplaint(Complaint complaint) {
        Boat boat = boatService.getBoatForReservation(complaint.getReservationId());
        complaint.setRentalId(boat.getId());
        String ownerEmail = userService.getUserById(boat.getBoatOwner().getId()).getEmail();
        complaint.setOwnerEmail(ownerEmail);

        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint makeVacationHomeComplaint(Complaint complaint) {
        VacationHome vacationHome = homeService.getVacationHomeForReservation(complaint.getReservationId());
        complaint.setRentalId(vacationHome.getId());
        String ownerEmail = userService.getUserById(vacationHome.getHomeOwner().getId()).getEmail();
        complaint.setOwnerEmail(ownerEmail);

        return complaintRepository.save(complaint);
    }
}
