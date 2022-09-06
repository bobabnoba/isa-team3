package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dao.BoatReservationInfo;
import com.ftn.fishingbooker.dao.ReservationCalendarInfo;
import com.ftn.fishingbooker.dao.ReservationInfo;
import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.dto.UtilityDto;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.Utility;
import com.ftn.fishingbooker.repository.ReservationRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private DateService dateService;
    @Autowired
    private UtilityService utilityService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private BoatService boatService;
    @Autowired
    @Lazy
    private AdventureService adventureService;
    @Autowired
    private SpecialOfferService specialOfferService;


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
    public Reservation ownerMakeReservation(Client client, ReservationDto reservationDto, Long objectId) {

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

        Reservation saved = reservationRepository.save(newReservation);
        if (reservationDto.getType().equals(ReservationType.VACATION_HOME)) {
            homeService.makeReservation(objectId, saved);
        } else {
            boatService.makeReservation(objectId, saved);
        }

        clientService.updatePoints(client, saved.getPrice());
        emailService.sendReservationEmail(ReservationMapper.map(saved), client);

        return saved;
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

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Reservation makeVacationHomeReservation(Client client, Long homeId, ReservationDto reservationDto) {
        Reservation reservation = makeNewReservation(client, reservationDto);

        try {
            Reservation newReservation = reservationRepository.save(reservation);
            homeService.makeReservation(homeId, newReservation);
            clientService.updatePoints(client, newReservation.getPrice());

            return newReservation;

        } catch (PessimisticLockingFailureException e) {
            System.out.println("Pessimistic lock: Vacation Home");
            throw  e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Reservation makeBoatReservation(Client client, Long boatId, ReservationDto reservationDto) {
        Reservation reservation = makeNewReservation(client, reservationDto);

        try {
            Reservation newReservation = reservationRepository.save(reservation);
            boatService.makeReservation(boatId, reservation);
            clientService.updatePoints(client, newReservation.getPrice());

            return newReservation;

        } catch (PessimisticLockingFailureException e) {
            System.out.println("Pessimistic lock: Vacation Home");
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Reservation makeAdventureReservation(Client client, Long adventureId, ReservationDto reservationDto) {
        Adventure adventure = adventureService.getById(adventureId);
        Reservation reservation = makeReservation(client, reservationDto, adventure.getDurationInHours());

        try {
            Reservation newReservation = reservationRepository.save(reservation);
            adventureService.makeReservation(adventureId, reservation);
            clientService.updatePoints(client, newReservation.getPrice());

            return newReservation;

        } catch (PessimisticLockingFailureException e) {
            System.out.println("Pessimistic lock: Vacation Home");
            throw e;
        }
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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Reservation makeSpecialOfferHomeReservation(Client client, Long rentalId, Long offerId, ReservationDto reservationDto) {
        Reservation reservation = makeNewSpecialOfferReservation(client, reservationDto);

        try {
            specialOfferService.reserveSpecialOffer(offerId);
            Reservation newReservation = reservationRepository.save(reservation);
            homeService.makeReservation(rentalId, reservation);
            clientService.updatePoints(client, newReservation.getPrice());

            return newReservation;

        } catch (Exception e) {
            System.out.println("Pessimistic lock: SpecialOfferReservation");

        }
        return null;

    }

    private Reservation makeNewSpecialOfferReservation(Client client, ReservationDto reservationDto) {
        Reservation reservation = ReservationMapper.map(reservationDto);
        reservation.setClient(client);
        var discount = reservationDto.getPrice() * client.getRank().getPercentage() / 100;
        reservation.setPrice(reservationDto.getPrice() - discount);

        Set<Utility> utilitySet = new HashSet<>();
        for (UtilityDto utilityDto : reservationDto.getUtilities()
        ) {
            Utility utility = utilityService.getByName(utilityDto.getName());
            utilitySet.add(utility);
        }
        reservation.setUtilities(utilitySet);
        return reservation;
    }

    private Reservation makeNewReservation(Client client, ReservationDto reservationDto) {
        Reservation reservation = ReservationMapper.map(reservationDto);
        reservation.setClient(client);
        reservation.setPrice(calculatePrice(reservationDto, client.getRank().getPercentage()));
        Set<Utility> utilitySet = new HashSet<>();
        for (UtilityDto utilityDto : reservationDto.getUtilities()
        ) {
            Utility utility = utilityService.getByName(utilityDto.getName());
            utilitySet.add(utility);
        }
        reservation.setUtilities(utilitySet);
        return reservation;
    }
    
    @Override
    public Collection<Reservation> getReservationsForBoatChart(Long id, Date from, Date to) {
        return reservationRepository.getReservationsForBoatChart(id, from, to);
    }

    @Override
    public Collection<Reservation> getReservationsForHomeChart(Long id, Date from, Date to) {
        return reservationRepository.getReservationsForHomeChart(id, from, to);
    }

    @Override
    public Collection<Reservation> getReservationsForAdventureChart(Long id, Date from, Date to) {
        return reservationRepository.getReservationsForAdventureChart(id, from, to);
    }

    @Override
    public Collection<Reservation> getReservationForBoatOwnerChart(Long id, Date from, Date to) {
        return reservationRepository.getReservationForBoatOwnerChart(id, from, to);
    }

    @Override
    public Collection<Reservation> getReservationForHomeOwnerChart(Long id, Date from, Date to) {
        return reservationRepository.getReservationForHomeOwnerChart(id, from, to);
    }

    @Override
    public Collection<Reservation> getReservationForInstructorChart(Long id, Date from, Date to) {
        return reservationRepository.getReservationForInstructorChart(id, from, to);
    }



}
