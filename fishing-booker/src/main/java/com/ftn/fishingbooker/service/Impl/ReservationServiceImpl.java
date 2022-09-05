package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dao.*;
import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.dto.UtilityDto;
import com.ftn.fishingbooker.enumeration.*;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final DateService dateService;
    private final UtilityService utilityService;

    @Override
    public Collection<Reservation> findAllForClient(Long clientId) {
        return reservationRepository.findAllForClient(clientId);
    }

    @Override
    public Collection<Reservation> getReservationForVacationHome(Long homeId) {
        return reservationRepository.getReservationForVacationHome(homeId);
    }

    @Override
    public Collection<Reservation> getReservationForBoat(Long boatId) {
        return reservationRepository.getReservationForBoat(boatId);
    }

    @Override
    public Collection<Reservation> getReservationsForAdventures(Collection<Long> ids) {
        return reservationRepository.getReservationsForAdventures(ids);
    }

    @Override
    public Collection<ReservationInfo> getUpcomingReservationsForInstructor(Long id) {
        return reservationRepository.getUpcomingReservationsForInstructor(id);
    }

    @Override
    public Collection<Reservation> getPastReservationsForInstructor(Long id) {
        return reservationRepository.getPastReservationsForInstructor(id);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ResourceConflictException("Reservation not found"));
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getOngoingReservationForInstructor(Long id) {
        return reservationRepository.getOngoingReservationForInstructor(id);
    }

    @Override
    public int getNoOfIncomingReservationsForAdventure(Long id) {
        return reservationRepository.noOfIncomingReservationsForAdventure(id);
    }

    @Override
    public int getNoOfIncomingReservationsForBoat(Long id) {
        return reservationRepository.noOfIncomingReservationsForBoat(id);
    }

    @Override
    public int getNoOfIncomingReservationsForVacationHome(Long id) {
        return reservationRepository.noOfIncomingReservationsForHome(id);
    }

    @Override
    public int getNoOfIncomingReservationsForUser(Long id, String role) {
        if (role.equalsIgnoreCase("ROLE_INSTRUCTOR")) {
            return reservationRepository.noOfIncomingReservationsForInstructor(id);
        } else if (role.equalsIgnoreCase("ROLE_BOAT_OWNER")) {
            return reservationRepository.noOfIncomingReservationsForBoatOwner(id);
        } else if (role.equalsIgnoreCase("ROLE_HOME_OWNER")) {
            return reservationRepository.noOfIncomingReservationsForHomeOwner(id);
        } else if (role.equalsIgnoreCase("ROLE_CLIENT")) {
            return reservationRepository.noOfIncomingReservationsForClient(id);
        } else return 0;
    }

    @Override
    public Collection<Reservation> getReservationsForClient(Long clientId) {
        return reservationRepository.findAllForClient(clientId);
    }

    @Override
    public Reservation makeReservation(Client client, ReservationDto reservationDto) {

        Reservation newReservation = ReservationMapper.map(reservationDto);
        newReservation.setClient(client);
        newReservation.setPrice(calculatePrice(reservationDto, client.getRank().getPercentage()));
        Set<Utility> utilitySet = new HashSet<>();
        for (UtilityDto utilityDto : reservationDto.getUtilities()
        ) {
            Utility utility = utilityService.getByName(utilityDto.getName());
            utilitySet.add(utility);
        }
        newReservation.setUtilities(utilitySet);
        return reservationRepository.save(newReservation);
    }

    @Override
    public Reservation makeSpecialOfferReservation(Client client, ReservationDto reservationDto) {
        Reservation newReservation = ReservationMapper.map(reservationDto);
        newReservation.setClient(client);
        var discount = reservationDto.getPrice() * client.getRank().getPercentage() / 100;
        newReservation.setPrice(reservationDto.getPrice() - discount);

        Set<Utility> utilitySet = new HashSet<>();
        for (UtilityDto utilityDto : reservationDto.getUtilities()
        ) {
            Utility utility = utilityService.getByName(utilityDto.getName());
            utilitySet.add(utility);
        }
        newReservation.setUtilities(utilitySet);
        return reservationRepository.save(newReservation);
    }


    @Override
    public Reservation makeReservation(Client client, ReservationDto reservationDto, double durationInHours) {
        Date newEndDate = dateService.addHoursToJavaUtilDate(reservationDto.getStartDate(), durationInHours);
        reservationDto.setEndDate(newEndDate);

        Reservation newReservation = ReservationMapper.map(reservationDto);
        newReservation.setClient(client);
        newReservation.setPrice(calculatePrice(reservationDto, client.getRank().getPercentage()));

        Set<Utility> utilitySet = new HashSet<>();
        for (UtilityDto utilityDto : reservationDto.getUtilities()
        ) {
            Utility utility = utilityService.getByName(utilityDto.getName());
            utilitySet.add(utility);
        }
        newReservation.setUtilities(utilitySet);

        return reservationRepository.save(newReservation);
    }

    @Override
    public boolean dateOverlapsWithReservation(Collection<Reservation> reservations, Date startDate, Date endDate) {
        if (reservations == null) {
            return false;
        }
        for (Reservation reservation : reservations) {
            if (dateService.doPeriodsOverlap(reservation.getStartDate(), reservation.getEndDate(), startDate, endDate)) {
                return true;
            }
        }
        return false;
    }


    private double calculatePrice(ReservationDto reservationDto, Double percentage) {

        try {
            var diff = dateService.DifferenceBetweenDates(reservationDto.getStartDate(), reservationDto.getEndDate());
            if (diff == 0) {
                diff = 1;
            }
            double utilities = 0.0;
            for (UtilityDto utility : reservationDto.getUtilities()
            ) {
                utilities += utility.getPrice();
            }
            var total = (reservationDto.getPrice() * diff + utilities);
            if (percentage != 0) {
                var discount = total * percentage / 100;
                return total - discount;
            } else {
                return total;
            }
        } catch (ParseException exception) {
            System.out.println(exception);
        }

        return 0;
    }

    @Override
    public Collection<BoatReservationInfo> getUpcomingReservationsForBoatOwner(Long id) {
        return reservationRepository.getUpcomingReservationsForBoatOwner(id);
    }

    @Override
    public Collection<Reservation> getPastReservationsForBoatOwner(Long id) {
        return reservationRepository.getPastReservationsForBoatOwner(id);
    }

    @Override
    public Collection<BoatReservationInfo> getCurrentReservationsForBoatOwner(Long id) {
        return reservationRepository.getCurrentReservationsForBoatOwner(id);
    }

    @Override
    public Reservation ownerMakeReservation(Client client, ReservationDto reservationDto) {
        //metoda bez racunanja cene opet
        //meni ovdje nikakvo racunjanje ne treba, stize izracunata cena s fronta, moze samo neka provjera mzd

        Reservation newReservation = ReservationMapper.map(reservationDto);
        newReservation.setClient(client);
        newReservation.setPrice(reservationDto.getPrice());
        Set<Utility> utilitySet = new HashSet<>();
        for (UtilityDto utilityDto : reservationDto.getUtilities()
        ) {
            Utility utility = utilityService.getByName(utilityDto.getName());
            utilitySet.add(utility);
        }
        newReservation.setUtilities(utilitySet);
        return reservationRepository.save(newReservation);
    }

    @Override
    public Reservation instructorMakeReservation(Client client, ReservationDto reservationDto, double durationInHours) {
        Date newEndDate = dateService.addHoursToJavaUtilDate(reservationDto.getStartDate(), durationInHours);
        reservationDto.setEndDate(newEndDate);

        Reservation newReservation = ReservationMapper.map(reservationDto);
        newReservation.setClient(client);
        newReservation.setPrice(reservationDto.getPrice());

        Set<Utility> utilitySet = new HashSet<>();
        for (UtilityDto utilityDto : reservationDto.getUtilities()
        ) {
            Utility utility = utilityService.getByName(utilityDto.getName());
            utilitySet.add(utility);
        }
        newReservation.setUtilities(utilitySet);

        return reservationRepository.save(newReservation);
    }

    @Override
    public Collection<ReservationCalendarInfo> getAllInstructorReservations(Long id) {
        return reservationRepository.getAllReservationForInstructor(id);
    }
    @Override
    public Collection<BoatReservationInfo> getUpcomingReservationsForHomeOwner(Long id) {
        return reservationRepository.getUpcomingReservationsForHomeOwner(id);
    }

    @Override
    public Collection<Reservation> getPastReservationsForHomeOwner(Long id) {
        return reservationRepository.getPastReservationsForHomeOwner(id);
    }

    @Override
    public Collection<BoatReservationInfo> getCurrentReservationsForHomeOwner(Long id) {
        return reservationRepository.getCurrentReservationsForHomeOwner(id);
    }

    @Override
    public Collection<Reservation> getCaptainReservationsForBoatOwner(Long id) {
        return reservationRepository.getCaptainReservationsForBoatOwner(id);
    }

    @Override
    public Collection<Reservation> getReservationsForBoat(Long id, Date from, Date to) {
        return reservationRepository.getReservationsForBoat(id, from, to);
    }

    @Override
    public Collection<Reservation> getReservationsForHome(Long id, Date from, Date to) {
        return reservationRepository.getReservationsForHome(id, from, to);
    }

    @Override
    public Collection<Reservation> getReservationsForAdventure(Long id, Date from, Date to) {
        return reservationRepository.getReservationsForAdventure(id, from, to);
    }

    @Override
    public Collection<Reservation> getReservationForBoatOwner(Long id, Date from, Date to) {
        return reservationRepository.getReservationForBoatOwner(id, from, to);
    }

    @Override
    public Collection<Reservation> getReservationForHomeOwner(Long id, Date from, Date to) {
        return reservationRepository.getReservationForHomeOwner(id, from, to);
    }

    @Override
    public Collection<Reservation> getReservationForInstructor(Long id, Date from, Date to) {
        return reservationRepository.getReservationForInstructor(id, from, to);
    }


}
