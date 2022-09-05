package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.exception.*;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import com.ftn.fishingbooker.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.*;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final HomeRepository vacationHomeRepository;
    private final ReservationService reservationService;
    private final DateService dateService;
    private final HomeOwnerService homeOwnerService;
    private final EarningsService earningsService;
    private final HomeOwnerRepository homeOwnerRepository;
    private final HomeAvailabilityService homeAvailabilityService;


    @Override
    public Collection<VacationHome> getAll() {
        return vacationHomeRepository.getAll();
    }

    @Override
    @Transactional
    public VacationHome getById(Long id) {
        return vacationHomeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Home with id " + id + " does not exist"));
    }

    @Override
    public Collection<VacationHome> filterAll(FilterDto filter) {
        ArrayList<VacationHome> homeList = (ArrayList<VacationHome>) vacationHomeRepository.getAll();
        ArrayList<VacationHome> filteredHomeList = new ArrayList<>();

        for (VacationHome home : homeList) {

            if (home.getGuestLimit() >= filter.getPeople()) {
                // Date should overlap with vacation home availability
                if (doPeriodsOverlap(filter.getStartDate(), filter.getEndDate(), home.getAvailability())) {

                    Collection<Reservation> reservations = reservationService.getReservationForVacationHome(home.getId());
                    boolean overlaps = reservationService.dateOverlapsWithReservation(reservations, filter.getStartDate(), filter.getEndDate());

                    if (!overlaps) {
                        filteredHomeList.add(home);
                    }
                }
            }
        }
        return filteredHomeList;
    }
    private boolean doPeriodsOverlap(Date startDate, Date endDate, Set<VacationHomeAvailability> availableTimePeriods) {

        for (VacationHomeAvailability period : availableTimePeriods) {
            if (dateService.doPeriodsOverlap(period.getStartDate(), period.getEndDate(), startDate, endDate)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void makeReservation(Long homeId, Reservation reservation) {
        VacationHome home = vacationHomeRepository.getById(homeId);
        home.getReservations().add(reservation);
        vacationHomeRepository.save(home);
        homeOwnerService.updatePoints(home.getHomeOwner(), reservation.getPrice());
        earningsService.saveEarnings(reservation, home.getHomeOwner().getEmail(), home.getHomeOwner().getRank());

    }

    @Override
    public VacationHome getVacationHomeForReservation(Long reservationId) {
        return vacationHomeRepository.getVacationHomeForReservation(reservationId);
    }

    @Override
    public Collection<VacationHome> getAllByOwner(String email) {
        HomeOwner owner = homeOwnerRepository.findByEmail(email);
        if (owner == null) {
            throw new ResourceConflictException("Home owner with email " + email + " does not exist");
        }
        return vacationHomeRepository.findAllByOwnerId(owner.getId());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        VacationHome found = vacationHomeRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVU VIKENDICU!
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForVacationHome(id);
        if ( !(noOfFutureRes > 0)){
            found.setDeleted(true);
            vacationHomeRepository.save(found);
        }
        //TODO: dodati povratnu poruku
    }

    @Override
    @Transactional
    public VacationHome addHome(VacationHome home, String ownerEmail) {
        HomeOwner owner = homeOwnerRepository.findByEmail(ownerEmail);
        home.setHomeOwner(owner);
        return vacationHomeRepository.save(home);

    }

    @Override
    @Transactional
    public VacationHome updateHomeInfo(Long id, HomeInfoDto updated) {
        VacationHome found = vacationHomeRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVAJ BROD!
        //ako ima ne moze se editovati ako nema edituj ga
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForVacationHome(id);
        if ( !(noOfFutureRes > 0)) {
            found.setName(updated.getName());
            found.setInformation(updated.getInformation());
            found.setDescription(updated.getDescription());
            found.setPricePerDay(updated.getPricePerDay());
            found.setCancelingPercentage(updated.getCancelingPercentage());
            found.setGuestLimit(updated.getGuestLimit());
        }
        return vacationHomeRepository.save(found);
    }

    @Override
    @Transactional
    public VacationHome updateHomeAdditionalInfo(Long id, HomeAdditionalInfo updated) {

        VacationHome found = vacationHomeRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVAJ BROD!
        //ako ima ne moze se editovati ako nema edituj ga
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForVacationHome(id);
        if ( !(noOfFutureRes > 0)) {
            found.setUtilities(new HashSet<>(updated.getUtilities()));
            found.setRooms(new HashSet<>(updated.getRooms().stream().map(VacationHomeMapper::mapToRoom).collect(Collectors.toSet())));
        }
        return vacationHomeRepository.save(found);
    }

    @Override
    @Transactional
    public VacationHome updateHomeRules(Long id, Collection<Rule> updated) {
        VacationHome found = vacationHomeRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVAJ BROD!
        //ako ima ne moze se editovati ako nema edituj ga
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForVacationHome(id);
        if ( !(noOfFutureRes > 0)) {
            found.setCodeOfConduct(new HashSet<>(updated));
        }
        return vacationHomeRepository.save(found);
    }

    @Override
    @Transactional
    public VacationHome updateHomeAddress(Long id, Address updated) {
        VacationHome found = vacationHomeRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVAJ BROD!
        //ako ima ne moze se editovati ako nema edituj ga
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForVacationHome(id);
        if ( !(noOfFutureRes > 0)) {
            found.setAddress(updated);
        }
        return vacationHomeRepository.save(found);
    }

    @Override
    @Transactional
    public void addImage(Long boatId, String fileName) {
        VacationHome found = vacationHomeRepository.findById(boatId)
                .orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        found.getImages().add(new Image(fileName));
        vacationHomeRepository.save(found);
    }

    public boolean isBetween(Date theOne, Date start, Date end){
        return theOne.after(start) && theOne.before(end);
    }

    @Override
    @Transactional
    public Collection<VacationHomeAvailability> addAvailabilityPeriod(VacationHomeAvailability newAvailability, Long homeId) {
        VacationHome home = vacationHomeRepository.findById(homeId).orElseThrow(() -> new ResourceConflictException("Home not found"));
        Set<VacationHomeAvailability> availabilities = new HashSet<>(home.getAvailability());

        Optional<VacationHomeAvailability> endOfNewPartOfSecond = availabilities.stream().filter(homeAvailability ->
                isBetween(newAvailability.getEndDate(), homeAvailability.getStartDate(), homeAvailability.getEndDate()) ||
                        newAvailability.getEndDate().equals(homeAvailability.getStartDate())).findFirst();
        Optional<VacationHomeAvailability> startOfNewPartOfFirst = availabilities.stream().filter(homeAvailability ->
                isBetween(newAvailability.getStartDate(), homeAvailability.getStartDate(), homeAvailability.getEndDate()) ||
                        newAvailability.getStartDate().equals(homeAvailability.getEndDate())).findFirst();

        VacationHomeAvailability missingPeriod = new VacationHomeAvailability();
        missingPeriod.setStartDate(newAvailability.getStartDate());
        missingPeriod.setEndDate(newAvailability.getEndDate());


        if( endOfNewPartOfSecond.isPresent() && startOfNewPartOfFirst.isPresent()){
            // novi zahvata pocetak jednog i kraj drugog
            if(!startOfNewPartOfFirst.equals(endOfNewPartOfSecond)){
                //6  nadji one koji se mzd nalaze izmedju ova 2
                var mybInBetween = availabilities.stream().filter(homeAvailability ->
                        isBetween(homeAvailability.getStartDate(),startOfNewPartOfFirst.get().getEndDate(), endOfNewPartOfSecond.get().getStartDate()) &&
                                isBetween(homeAvailability.getEndDate(),startOfNewPartOfFirst.get().getEndDate(), endOfNewPartOfSecond.get().getStartDate())).collect(Collectors.toSet());

                //4 i 5 mybInBetween je potrebno izbrisati ako postoji  i kreirati samo jedan novi
                if ( !mybInBetween.isEmpty()){
                    missingPeriod.setStartDate(startOfNewPartOfFirst.get().getEndDate());
                    missingPeriod.setEndDate(endOfNewPartOfSecond.get().getStartDate());
                    //startOfNewPartOfFirst i endOfNewPartOfSecond brisemo
                    //ujesto njih kreiram novi koji kopicinje kad pocetak startOfNewPartOfFirst i zavrsava se kad kraj endOfNewPartOfSecond
                    VacationHomeAvailability newOne = new VacationHomeAvailability();
                    newOne.setStartDate(startOfNewPartOfFirst.get().getStartDate());
                    newOne.setEndDate(endOfNewPartOfSecond.get().getEndDate());
                    var saved = homeAvailabilityService.save(newOne);
                    availabilities.add(saved);

                    availabilities.remove(startOfNewPartOfFirst.get());
                    availabilities.remove(endOfNewPartOfSecond.get());
                    homeAvailabilityService.delete(startOfNewPartOfFirst.get().getId());
                    homeAvailabilityService.delete(endOfNewPartOfSecond.get().getId());

                    //OBRISI IH i ukloni iz broda
                    availabilities.removeAll(mybInBetween);
                    homeAvailabilityService.delete(mybInBetween);

                    home.setAvailability(availabilities);
                    vacationHomeRepository.save(home);
                }else{
                    //3 zahvata pocetak jednog a kraj drugog, pri tome ta dva su jedan pored drugog
                    missingPeriod.setStartDate(startOfNewPartOfFirst.get().getEndDate());
                    missingPeriod.setEndDate(endOfNewPartOfSecond.get().getStartDate());
                    //startOfNewPartOfFirst i endOfNewPartOfSecond brisemo
                    //ujesto njih kreiram novi koji kopicinje kad pocetak startOfNewPartOfFirst i zavrsava se kad kraj endOfNewPartOfSecond
                    VacationHomeAvailability newOne = new VacationHomeAvailability();
                    newOne.setStartDate(startOfNewPartOfFirst.get().getStartDate());
                    newOne.setEndDate(endOfNewPartOfSecond.get().getEndDate());
                    var saved  = homeAvailabilityService.save(newOne);
                    availabilities.add(saved);

                    availabilities.remove(startOfNewPartOfFirst.get());
                    availabilities.remove(endOfNewPartOfSecond.get());
                    homeAvailabilityService.delete(startOfNewPartOfFirst.get().getId());
                    homeAvailabilityService.delete(endOfNewPartOfSecond.get().getId());

                    home.setAvailability(availabilities);
                }

            }else {
                //7 taj period vec posoji i ne radi nista vec obradjeno
                return home.getAvailability();
            }
            //TODO:sacuvati sve ovo
            vacationHomeRepository.save(home);
            return home.getAvailability();

        }

//doraditi
        var entirelyInsideNewOne = availabilities.stream().filter(homeAvailability ->
                (isBetween(homeAvailability.getStartDate(), newAvailability.getStartDate(), newAvailability.getEndDate())  ||
                        homeAvailability.getStartDate().getTime() == (newAvailability.getStartDate().getTime()))&&
                        (isBetween(homeAvailability.getEndDate(), newAvailability.getStartDate(), newAvailability.getEndDate()) ||
                                homeAvailability.getEndDate().getTime() == (newAvailability.getEndDate().getTime()))).collect(Collectors.toSet());
        if(!entirelyInsideNewOne.isEmpty()){

            //KREIRAJ NOVI
            VacationHomeAvailability added = homeAvailabilityService.save(newAvailability);
            availabilities.add(added);
            //home.setAvailableTimePeriods(new HashSet<>(checkForOverlapping(added, new ArrayList<>(availabilities))));

            //izbaci iz liste kod broda
            availabilities.removeAll(entirelyInsideNewOne);
            home.setAvailability(availabilities);
            vacationHomeRepository.save(home);
            //TODO:OBRISI IH SVE
            homeAvailabilityService.delete(entirelyInsideNewOne);
            //ovo ce na frontu da sjebe stvar vrv ali pri ponovnom ucitavanju trebalo bi da bude ok
            return home.getAvailability();
        }else{
            Optional<VacationHomeAvailability> endPartOfNew = availabilities.stream().filter(homeAvailability ->
                    isBetween(homeAvailability.getEndDate(), newAvailability.getStartDate(), newAvailability.getEndDate()) ||
                            homeAvailability.getEndDate().equals(newAvailability.getStartDate())).findFirst();
            Optional<VacationHomeAvailability> startPartOfNew = availabilities.stream().filter(homeAvailability ->
                    isBetween(homeAvailability.getStartDate(), newAvailability.getStartDate(), newAvailability.getEndDate()) ||
                            homeAvailability.getStartDate().equals(newAvailability.getEndDate())).findFirst();

            if(!(endPartOfNew.isPresent() && startPartOfNew.isPresent())){
                //1 novi zahvata kraj postojeceg
                if( endPartOfNew.isPresent() ){
                    missingPeriod.setStartDate(endPartOfNew.get().getStartDate());
                    endPartOfNew.get().setEndDate(newAvailability.getEndDate());
                    vacationHomeRepository.save(home);
                    return home.getAvailability();
                }
                //2 novi zahvata pocetak postojeceg
                if( startPartOfNew.isPresent() ){
                    missingPeriod.setEndDate(startPartOfNew.get().getEndDate());
                    startPartOfNew.get().setStartDate(newAvailability.getStartDate());
                    vacationHomeRepository.save(home);
                    return home.getAvailability();
                }


            }else{
                //ovdje zeleno 5
                var anyInBetween = availabilities.stream().filter(homeAvailability ->
                        isBetween(homeAvailability.getStartDate(),endPartOfNew.get().getEndDate(), startPartOfNew.get().getStartDate()) &&
                                isBetween(homeAvailability.getEndDate(), endPartOfNew.get().getEndDate(), startPartOfNew.get().getStartDate())).collect(Collectors.toSet());
                VacationHomeAvailability newOne = new VacationHomeAvailability();
                newOne.setStartDate(newAvailability.getStartDate());
                newOne.setEndDate(newAvailability.getEndDate());
                homeAvailabilityService.save(newOne);
                availabilities.add(newOne);

                availabilities.remove(endPartOfNew.get());
                availabilities.remove(startPartOfNew.get());
                homeAvailabilityService.delete(endPartOfNew.get().getId());
                homeAvailabilityService.delete(startPartOfNew.get().getId());


                if(!anyInBetween.isEmpty()){
                    availabilities.remove(anyInBetween);
                    homeAvailabilityService.delete(anyInBetween);
                }
                home.setAvailability(availabilities);
                vacationHomeRepository.save(home);
                return home.getAvailability();
            }
        }

        //u slucaju da nema nikakvih preklapanja samo ga dodaj
        VacationHomeAvailability added = homeAvailabilityService.save(newAvailability);
        availabilities.add(added);
        home.setAvailability(availabilities);
        vacationHomeRepository.save(home);
        return home.getAvailability();

    }

    @Override
    public boolean checkAvailability(Date from, Date to, Long homeId) {
        boolean isAvailable = false;
        VacationHome home = vacationHomeRepository.findById(homeId).orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        List<VacationHomeAvailability> availabilityPeriods = new ArrayList<>(home.getAvailability());

        for (VacationHomeAvailability period : availabilityPeriods) {
            if ((from.after(period.getStartDate()) || from.equals(period.getStartDate())) && (to.before(period.getEndDate()) || to.equals(period.getEndDate()))) {
                isAvailable = true;
                break;
            }
        }
        return isAvailable;
    }

    @Override
    public VacationHome getHomeForReservation(Long reservationId) {
        return vacationHomeRepository.getVacationHomeForReservation(reservationId);
    }

    @Override
    public VacationHome save(VacationHome home) {
        return vacationHomeRepository.save(home);
    }

    @Override
    @Transactional
    public Collection<VacationHomeAvailability> updateAvailability(Date reservationStartDate, Date reservationEndDate, Long id) {
        //TODO:
        VacationHome home = vacationHomeRepository.findById(id).orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        Set<VacationHomeAvailability> availabilities = new HashSet<>(home.getAvailability());

        var availabilityInBetween = availabilities.stream().filter(homeAvailability ->
                isBetween(reservationStartDate,homeAvailability.getStartDate(), homeAvailability.getEndDate()) &&
                        isBetween(reservationEndDate, homeAvailability.getStartDate(),homeAvailability.getEndDate())).findFirst();
        var onStart = availabilities.stream().filter(homeAvailability ->
                reservationStartDate.getTime() == homeAvailability.getStartDate().getTime() &&
                        isBetween(reservationEndDate, homeAvailability.getStartDate(), homeAvailability.getEndDate())).findFirst();
        var onEnd = availabilities.stream().filter(homeAvailability ->
                reservationEndDate.getTime() == homeAvailability.getEndDate().getTime() &&
                        isBetween(reservationStartDate, homeAvailability.getStartDate(), homeAvailability.getEndDate())).findFirst();
        var theOne = availabilities.stream().filter( homeAvailability ->
                homeAvailability.getStartDate().getTime() == reservationStartDate.getTime() && homeAvailability.getEndDate().getTime() == reservationEndDate.getTime()).findFirst();

        if ( theOne.isPresent()){
            availabilities.remove(theOne.get());
            home.setAvailability(availabilities);
            vacationHomeRepository.save(home);
            homeAvailabilityService.delete(theOne.get().getId());
            return home.getAvailability();
        }else if (onStart.isPresent()){
            VacationHomeAvailability newOne = new VacationHomeAvailability();
            newOne.setStartDate(DateUtil.addDays(reservationEndDate,1));
            newOne.setEndDate(onStart.get().getEndDate());
            var saved = homeAvailabilityService.save(newOne);
            availabilities.add(saved);
            availabilities.remove(onStart.get());
            home.setAvailability(availabilities);
            vacationHomeRepository.save(home);
            homeAvailabilityService.delete(onStart.get().getId());
            return home.getAvailability();
        }else if (onEnd.isPresent()){
            VacationHomeAvailability newOne = new VacationHomeAvailability();
            newOne.setStartDate(onEnd.get().getStartDate());
            newOne.setEndDate(DateUtil.addDays(reservationStartDate, -1));
            var saved = homeAvailabilityService.save(newOne);
            availabilities.add(saved);
            availabilities.remove(onEnd.get());
            home.setAvailability(availabilities);
            vacationHomeRepository.save(home);
            homeAvailabilityService.delete(onEnd.get().getId());
            return home.getAvailability();
        }else if (availabilityInBetween.isPresent()){
            VacationHomeAvailability newOne = new VacationHomeAvailability();
            newOne.setStartDate(DateUtil.addDays(reservationEndDate,1));
            newOne.setEndDate(availabilityInBetween.get().getEndDate());
            var saved = homeAvailabilityService.save(newOne);
            availabilities.add(saved);
            VacationHomeAvailability anotherNew = new VacationHomeAvailability();
            anotherNew.setStartDate(availabilityInBetween.get().getStartDate());
            anotherNew.setEndDate(DateUtil.addDays(reservationStartDate, -1));
            var anotherSaved = homeAvailabilityService.save(anotherNew);
            availabilities.add(anotherSaved);

            availabilities.remove(availabilityInBetween.get());
            home.setAvailability(availabilities);
            vacationHomeRepository.save(home);
            homeAvailabilityService.delete(availabilityInBetween.get().getId());
            return home.getAvailability();
        }
        return home.getAvailability();
    }

    @Override
    public int getNoOfIncomingReservations(Long id) {
        return reservationService.getNoOfIncomingReservationsForVacationHome(id);
    }

    @Override
    public Boolean checkIfReservationOverlapsAvailability(VacationHomeAvailability newAvailability, Long homeId) {
        VacationHome home = vacationHomeRepository.findById(homeId).orElseThrow(() -> new ResourceConflictException("Home not found"));
        //TODO:PROVERITI DA LI IMA REZ U TOM PERIODU
        Collection<Reservation> homeReservations = reservationService.getReservationForVacationHome(homeId);
        var foundOverlaps = homeReservations.stream().filter(reservation -> dateService.reservationOverlapsWithAvailability(reservation.getStartDate(), reservation.getEndDate(), newAvailability.getStartDate(), newAvailability.getEndDate())).collect(Collectors.toSet());
        if(foundOverlaps.size() > 0){
            return true;
        }
        return false;
    }


}
