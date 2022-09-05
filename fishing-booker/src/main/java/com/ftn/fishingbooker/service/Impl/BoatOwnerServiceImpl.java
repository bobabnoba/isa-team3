package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dao.*;
import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import com.ftn.fishingbooker.util.*;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.util.*;
import java.util.stream.*;

@Service
public class BoatOwnerServiceImpl implements BoatOwnerService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;
    private final UserRankService userRankService;
    private final BoatOwnerRepository boatOwnerRepository;
    private final BoatOwnerAvailabilityService boatOwnerAvailabilityService;
    private final ReservationService reservationService;
    private final DateService dateService;

    public BoatOwnerServiceImpl(UserService userService, UserRepository userRepository, RegistrationRepository registrationRepository,
                                UserRankService userRankService, BoatOwnerRepository boatOwnerRepository,
                                BoatOwnerAvailabilityService boatOwnerAvailabilityService, ReservationService reservationService,
                                DateService dateService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.registrationRepository = registrationRepository;
        this.userRankService = userRankService;
        this.boatOwnerRepository = boatOwnerRepository;
        this.boatOwnerAvailabilityService = boatOwnerAvailabilityService;
        this.reservationService = reservationService;
        this.dateService = dateService;

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


    @Override
    public BoatOwner getByEmail(String boatOwnerEmail) {
        BoatOwner owner = boatOwnerRepository.findByEmail(boatOwnerEmail);
        return owner;
    }

    @Transactional
    @Override
    public Collection<BoatOwnerAvailability> addAvailabilityPeriod(BoatOwnerAvailability newAvailability, String email) {
        BoatOwner owner = boatOwnerRepository.findByEmail(email);
        //TODO:PROVERITI DA LI IMA REZ U TOM PERIODU
        Collection<Reservation> boatReservations = reservationService.getCaptainReservationsForBoatOwner(owner.getId());
        var foundOverlaps = boatReservations.stream().filter(reservation -> dateService.reservationOverlapsWithAvailability(reservation.getStartDate(), reservation.getEndDate(), newAvailability.getStartDate(), newAvailability.getEndDate())).collect(Collectors.toSet());
        if(foundOverlaps.size() > 0){
            return owner.getAvailability();
        }
        Set<BoatOwnerAvailability> availabilities = new HashSet<>(owner.getAvailability());

        Optional<BoatOwnerAvailability> endOfNewPartOfSecond = availabilities.stream().filter(boatAvailability ->
                isBetween(newAvailability.getEndDate(), boatAvailability.getStartDate(), boatAvailability.getEndDate()) ||
                        newAvailability.getEndDate().equals(boatAvailability.getStartDate())).findFirst();
        Optional<BoatOwnerAvailability> startOfNewPartOfFirst = availabilities.stream().filter(boatAvailability ->
                isBetween(newAvailability.getStartDate(), boatAvailability.getStartDate(), boatAvailability.getEndDate()) ||
                        newAvailability.getStartDate().equals(boatAvailability.getEndDate())).findFirst();

        BoatOwnerAvailability missingPeriod = new BoatOwnerAvailability();
        missingPeriod.setStartDate(newAvailability.getStartDate());
        missingPeriod.setEndDate(newAvailability.getEndDate());


        if( endOfNewPartOfSecond.isPresent() && startOfNewPartOfFirst.isPresent()){
            // novi zahvata pocetak jednog i kraj drugog
            if(!startOfNewPartOfFirst.equals(endOfNewPartOfSecond)){
                //6  nadji one koji se mzd nalaze izmedju ova 2
                var mybInBetween = availabilities.stream().filter(boatAvailability ->
                        isBetween(boatAvailability.getStartDate(),startOfNewPartOfFirst.get().getEndDate(), endOfNewPartOfSecond.get().getStartDate()) &&
                                isBetween(boatAvailability.getEndDate(),startOfNewPartOfFirst.get().getEndDate(), endOfNewPartOfSecond.get().getStartDate())).collect(Collectors.toSet());

                //4 i 5 mybInBetween je potrebno izbrisati ako postoji  i kreirati samo jedan novi
                if ( !mybInBetween.isEmpty()){
                    missingPeriod.setStartDate(startOfNewPartOfFirst.get().getEndDate());
                    missingPeriod.setEndDate(endOfNewPartOfSecond.get().getStartDate());
                    //startOfNewPartOfFirst i endOfNewPartOfSecond brisemo
                    //ujesto njih kreiram novi koji kopicinje kad pocetak startOfNewPartOfFirst i zavrsava se kad kraj endOfNewPartOfSecond
                    BoatOwnerAvailability newOne = new BoatOwnerAvailability();
                    newOne.setStartDate(startOfNewPartOfFirst.get().getStartDate());
                    newOne.setEndDate(endOfNewPartOfSecond.get().getEndDate());
                    var saved = boatOwnerAvailabilityService.save(newOne);
                    availabilities.add(saved);

                    availabilities.remove(startOfNewPartOfFirst.get());
                    availabilities.remove(endOfNewPartOfSecond.get());
                    boatOwnerAvailabilityService.delete(startOfNewPartOfFirst.get().getId());
                    boatOwnerAvailabilityService.delete(endOfNewPartOfSecond.get().getId());

                    //OBRISI IH i ukloni iz broda
                    availabilities.removeAll(mybInBetween);
                    boatOwnerAvailabilityService.delete(mybInBetween);

                    owner.setAvailability(availabilities);
                    boatOwnerRepository.save(owner);
                }else{
                    //3 zahvata pocetak jednog a kraj drugog, pri tome ta dva su jedan pored drugog
                    missingPeriod.setStartDate(startOfNewPartOfFirst.get().getEndDate());
                    missingPeriod.setEndDate(endOfNewPartOfSecond.get().getStartDate());
                    //startOfNewPartOfFirst i endOfNewPartOfSecond brisemo
                    //ujesto njih kreiram novi koji kopicinje kad pocetak startOfNewPartOfFirst i zavrsava se kad kraj endOfNewPartOfSecond
                    BoatOwnerAvailability newOne = new BoatOwnerAvailability();
                    newOne.setStartDate(startOfNewPartOfFirst.get().getStartDate());
                    newOne.setEndDate(endOfNewPartOfSecond.get().getEndDate());
                    var saved  = boatOwnerAvailabilityService.save(newOne);
                    availabilities.add(saved);

                    availabilities.remove(startOfNewPartOfFirst.get());
                    availabilities.remove(endOfNewPartOfSecond.get());
                    boatOwnerAvailabilityService.delete(startOfNewPartOfFirst.get().getId());
                    boatOwnerAvailabilityService.delete(endOfNewPartOfSecond.get().getId());

                    owner.setAvailability(availabilities);
                }

            }else {
                //7 taj period vec posoji i ne radi nista vec obradjeno
               // return null;
                return  owner.getAvailability();
            }
            //TODO:sacuvati sve ovo
            boatOwnerRepository.save(owner);
            return owner.getAvailability();

        }

//doraditi
        var entirelyInsideNewOne = availabilities.stream().filter(boatAvailability ->
                (isBetween(boatAvailability.getStartDate(), newAvailability.getStartDate(), newAvailability.getEndDate())  ||
                        boatAvailability.getStartDate().getTime() == (newAvailability.getStartDate().getTime()))&&
                        (isBetween(boatAvailability.getEndDate(), newAvailability.getStartDate(), newAvailability.getEndDate()) ||
                                boatAvailability.getEndDate().getTime() == (newAvailability.getEndDate().getTime()))).collect(Collectors.toSet());
        if(!entirelyInsideNewOne.isEmpty()){

            //KREIRAJ NOVI
            BoatOwnerAvailability added = boatOwnerAvailabilityService.save(newAvailability);
            availabilities.add(added);
            //boat.setAvailableTimePeriods(new HashSet<>(checkForOverlapping(added, new ArrayList<>(availabilities))));

            //izbaci iz liste kod broda
            availabilities.removeAll(entirelyInsideNewOne);
            owner.setAvailability(availabilities);
            boatOwnerRepository.save(owner);
            //TODO:OBRISI IH SVE
            boatOwnerAvailabilityService.delete(entirelyInsideNewOne);
            //ovo ce na frontu da sjebe stvar vrv ali pri ponovnom ucitavanju trebalo bi da bude ok
            return owner.getAvailability();
        }else{
            Optional<BoatOwnerAvailability> endPartOfNew = availabilities.stream().filter(boatAvailability ->
                    isBetween(boatAvailability.getEndDate(), newAvailability.getStartDate(), newAvailability.getEndDate()) ||
                            boatAvailability.getEndDate().equals(newAvailability.getStartDate())).findFirst();
            Optional<BoatOwnerAvailability> startPartOfNew = availabilities.stream().filter(boatAvailability ->
                    isBetween(boatAvailability.getStartDate(), newAvailability.getStartDate(), newAvailability.getEndDate()) ||
                            boatAvailability.getStartDate().equals(newAvailability.getEndDate())).findFirst();

            if(!(endPartOfNew.isPresent() && startPartOfNew.isPresent())){
                //1 novi zahvata kraj postojeceg
                if( endPartOfNew.isPresent() ){
                    missingPeriod.setStartDate(endPartOfNew.get().getStartDate());
                    endPartOfNew.get().setEndDate(newAvailability.getEndDate());
                    boatOwnerRepository.save(owner);
                    return owner.getAvailability();
                }
                //2 novi zahvata pocetak postojeceg
                if( startPartOfNew.isPresent() ){
                    missingPeriod.setEndDate(startPartOfNew.get().getEndDate());
                    startPartOfNew.get().setStartDate(newAvailability.getStartDate());
                    boatOwnerRepository.save(owner);
                    return owner.getAvailability();
                }


            }else{
                //ovdje zeleno 5
                var anyInBetween = availabilities.stream().filter(boatAvailability ->
                        isBetween(boatAvailability.getStartDate(),endPartOfNew.get().getEndDate(), startPartOfNew.get().getStartDate()) &&
                                isBetween(boatAvailability.getEndDate(), endPartOfNew.get().getEndDate(), startPartOfNew.get().getStartDate())).collect(Collectors.toSet());
                BoatOwnerAvailability newOne = new BoatOwnerAvailability();
                newOne.setStartDate(newAvailability.getStartDate());
                newOne.setEndDate(newAvailability.getEndDate());
                boatOwnerAvailabilityService.save(newOne);
                availabilities.add(newOne);

                availabilities.remove(endPartOfNew.get());
                availabilities.remove(startPartOfNew.get());
                boatOwnerAvailabilityService.delete(endPartOfNew.get().getId());
                boatOwnerAvailabilityService.delete(startPartOfNew.get().getId());


                if(!anyInBetween.isEmpty()){
                    availabilities.remove(anyInBetween);
                    boatOwnerAvailabilityService.delete(anyInBetween);
                }
                owner.setAvailability(availabilities);
                boatOwnerRepository.save(owner);
                return owner.getAvailability();
            }
        }

        //u slucaju da nema nikakvih preklapanja samo ga dodaj
        BoatOwnerAvailability added = boatOwnerAvailabilityService.save(newAvailability);
        availabilities.add(added);
        owner.setAvailability(availabilities);
        boatOwnerRepository.save(owner);
        return owner.getAvailability();
    }

    @Override
    @Transactional
    public Boolean checkIfReservationOverlapsAvailability(BoatOwnerAvailability newAvailability, String email) {
        BoatOwner owner = boatOwnerRepository.findByEmail(email);
        //TODO:PROVERITI DA LI IMA REZ U TOM PERIODU
        Collection<Reservation> ownerReservations = reservationService.getCaptainReservationsForBoatOwner(owner.getId());
        var foundOverlaps = ownerReservations.stream().filter(reservation -> dateService.reservationOverlapsWithAvailability(reservation.getStartDate(), reservation.getEndDate(), newAvailability.getStartDate(), newAvailability.getEndDate())).collect(Collectors.toSet());
        if(foundOverlaps.size() > 0){
            return true;
        }
        return false;
    }
}
