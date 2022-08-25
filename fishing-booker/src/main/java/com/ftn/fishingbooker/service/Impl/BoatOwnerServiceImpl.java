package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BoatOwnerServiceImpl implements BoatOwnerService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;
    private final UserRankService userRankService;
    private final BoatOwnerRepository boatOwnerRepository;

    public BoatOwnerServiceImpl(UserService userService, UserRepository userRepository, RegistrationRepository registrationRepository,
                                UserRankService userRankService, BoatOwnerRepository boatOwnerRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.registrationRepository = registrationRepository;
        this.userRankService = userRankService;
        this.boatOwnerRepository = boatOwnerRepository;
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
            if (from.after(period.getStartDate()) && to.before(period.getEndDate())) {
                isAvailable = true;
                break;
            }
        }
        return isAvailable;
    }
}
