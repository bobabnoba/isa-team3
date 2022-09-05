package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.util.*;
import org.springframework.stereotype.*;
import com.ftn.fishingbooker.service.BoatOwnerAvailabilityService;
import com.ftn.fishingbooker.service.BoatOwnerService;
import com.ftn.fishingbooker.service.UserRankService;
import com.ftn.fishingbooker.service.UserService;

import javax.transaction.*;
import java.util.*;

@Service
public class BoatOwnerServiceImpl implements BoatOwnerService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;
    private final UserRankService userRankService;
    private final BoatOwnerRepository boatOwnerRepository;
    private final BoatOwnerAvailabilityService boatOwnerAvailabilityService;

    public BoatOwnerServiceImpl(UserService userService, UserRepository userRepository, RegistrationRepository registrationRepository,
                                UserRankService userRankService, BoatOwnerRepository boatOwnerRepository,
                                BoatOwnerAvailabilityService boatOwnerAvailabilityService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.registrationRepository = registrationRepository;
        this.userRankService = userRankService;
        this.boatOwnerRepository = boatOwnerRepository;
        this.boatOwnerAvailabilityService = boatOwnerAvailabilityService;

    }

    @Override
    public User register(BoatOwner boatOwner, String motivation) throws ResourceConflictException {
        if (userService.isEmailRegistered(boatOwner.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }

        Registration registration = new Registration(RegistrationType.VACATION_BOAT_ADVERTISER, motivation, boatOwner.getEmail());
        registrationRepository.save(registration);

        return userRepository.save(boatOwner);
    }

    @Override
    public void updatePoints(BoatOwner owner, double reservationPrice) {
        owner.setPoints(owner.getPoints() + reservationPrice * owner.getRank().getReservationPercentage() / 100);

        userRankService.getLoyaltyProgram().forEach(rank -> {
            if (rank.getName().contains("ADVERTISER") && rank.getMinPoints() < owner.getPoints()){
                owner.setRank(rank);
            }
        });
        userRepository.save(owner);
    }

    @Override
    public BoatOwner getWithAvailability(String email) {
        return boatOwnerRepository.findByEmail(email);
    }

    @Override
    public boolean checkAvailability(Date from, Date to, String boatOwnerEmail) {
        boolean isAvailable = false;
        BoatOwner owner = boatOwnerRepository.findByEmail(boatOwnerEmail);
        List<BoatOwnerAvailability> availabilityPeriods = new ArrayList<>(owner.getAvailability());

        for (BoatOwnerAvailability period : availabilityPeriods) {
            if ((from.after(period.getStartDate()) || from.equals(period.getStartDate())) && (to.before(period.getEndDate()) || to.equals(period.getEndDate()))) {
                isAvailable = true;
                break;
            }
        }
        return isAvailable;
    }

    public boolean isBetween(Date theOne, Date start, Date end){
        return theOne.after(start) && theOne.before(end);
    }

    @Transactional
    @Override
    public void updateAvailability(Date reservationStartDate, Date reservationEndDate, String ownerEmail) {
        BoatOwner boatOwner = boatOwnerRepository.findByEmail(ownerEmail);
        if (boatOwner == null) throw  new ResourceConflictException("Boat owner not found");
        Set<BoatOwnerAvailability> availabilities = new HashSet<>(boatOwner.getAvailability());

        var availabilityInBetween = availabilities.stream().filter(boatOwnerAvailability ->
                isBetween(reservationStartDate,boatOwnerAvailability.getStartDate(), boatOwnerAvailability.getEndDate()) &&
                        isBetween(reservationEndDate, boatOwnerAvailability.getStartDate(),boatOwnerAvailability.getEndDate())).findFirst();
        var onStart = availabilities.stream().filter(boatOwnerAvailability ->
                reservationStartDate.getTime() == boatOwnerAvailability.getStartDate().getTime() &&
                        isBetween(reservationEndDate, boatOwnerAvailability.getStartDate(), boatOwnerAvailability.getEndDate())).findFirst();
        var onEnd = availabilities.stream().filter(boatOwnerAvailability ->
                reservationEndDate.getTime() == boatOwnerAvailability.getEndDate().getTime() &&
                        isBetween(reservationStartDate, boatOwnerAvailability.getStartDate(), boatOwnerAvailability.getEndDate())).findFirst();
        var theOne = availabilities.stream().filter( boatOwnerAvailability ->
                boatOwnerAvailability.getStartDate().getTime() == reservationStartDate.getTime() && boatOwnerAvailability.getEndDate().getTime() == reservationEndDate.getTime()).findFirst();

        if ( theOne.isPresent()){
            availabilities.remove(theOne.get());
            boatOwner.setAvailability(availabilities);
            boatOwnerRepository.save(boatOwner);
            boatOwnerAvailabilityService.delete(theOne.get().getId());
        }else if (onStart.isPresent()){
            BoatOwnerAvailability newOne = new BoatOwnerAvailability();
            newOne.setStartDate(DateUtil.addDays(reservationEndDate,1));
            newOne.setEndDate(onStart.get().getEndDate());
            var saved = boatOwnerAvailabilityService.save(newOne);
            availabilities.add(saved);
            availabilities.remove(onStart.get());
            boatOwner.setAvailability(availabilities);
            boatOwnerRepository.save(boatOwner);
            boatOwnerAvailabilityService.delete(onStart.get().getId());
        }else if (onEnd.isPresent()){
            BoatOwnerAvailability newOne = new BoatOwnerAvailability();
            newOne.setStartDate(onEnd.get().getStartDate());
            newOne.setEndDate(DateUtil.addDays(reservationStartDate, -1));
            var saved = boatOwnerAvailabilityService.save(newOne);
            availabilities.add(saved);
            availabilities.remove(onEnd.get());
            boatOwner.setAvailability(availabilities);
            boatOwnerRepository.save(boatOwner);
            boatOwnerAvailabilityService.delete(onEnd.get().getId());

        }else if (availabilityInBetween.isPresent()){
            BoatOwnerAvailability newOne = new BoatOwnerAvailability();
            newOne.setStartDate(DateUtil.addDays(reservationEndDate,1));
            newOne.setEndDate(availabilityInBetween.get().getEndDate());
            var saved = boatOwnerAvailabilityService.save(newOne);
            availabilities.add(saved);
            BoatOwnerAvailability anotherNew = new BoatOwnerAvailability();
            anotherNew.setStartDate(availabilityInBetween.get().getStartDate());
            anotherNew.setEndDate(DateUtil.addDays(reservationStartDate, -1));
            var anotherSaved = boatOwnerAvailabilityService.save(anotherNew);
            availabilities.add(anotherSaved);

            availabilities.remove(availabilityInBetween.get());
            boatOwner.setAvailability(availabilities);
            boatOwnerRepository.save(boatOwner);
            boatOwnerAvailabilityService.delete(availabilityInBetween.get().getId());
        }
    }

//    @Override
//    public Collection<ReservationInfo> getUpcomingReservationsForBoatOwner(String email) {
//        BoatOwner owner = boatOwnerRepository.findByEmail(email);
//        return reservationService.getUpcomingReservationsForBoatOwner(owner.getId());
//    }
//
//    @Override
//    public Collection<Reservation> getPastReservationsForBoatOwner(String email) {
//        BoatOwner owner = boatOwnerRepository.findByEmail(email);
//        return reservationService.getPastReservationsForBoatOwner(owner.getId());
//    }
//
//    @Override
//    public Collection<Reservation> getCurrentReservationsForBoatOwner(String email) {
//        BoatOwner owner = boatOwnerRepository.findByEmail(email);
//        return reservationService.getCurrentReservationsForBoatOwner(owner.getId());
//    }

    @Override
    public BoatOwner getByEmail(String boatOwnerEmail) {
        BoatOwner owner = boatOwnerRepository.findByEmail(boatOwnerEmail);
        return owner;
    }
}
