package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.BoatAdditionalInfo;
import com.ftn.fishingbooker.dto.BoatInfo;
import com.ftn.fishingbooker.dto.FilterDto;
import com.ftn.fishingbooker.enumeration.BoatType;
import com.ftn.fishingbooker.enumeration.NavigationType;
import com.ftn.fishingbooker.exception.EntityNotFoundException;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.BoatOwnerRepository;
import com.ftn.fishingbooker.repository.BoatRepository;
import com.ftn.fishingbooker.service.*;
import com.ftn.fishingbooker.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoatServiceImpl implements BoatService {
    @Autowired
    private BoatRepository boatRepository;
    @Autowired
    private DateService dateService;
    @Autowired
    @Lazy
    private ReservationService reservationService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private BoatOwnerRepository boatOwnerRepository;
    @Autowired
    private EarningsService earningsService;
    @Autowired
    private BoatAvailabilityService boatAvailabilityService;

    @Override
    public Collection<Boat> getAll() {
        return boatRepository.getAll();
    }

    @Override
    public Collection<Boat> filterAll(FilterDto filter) {
        ArrayList<Boat> boatsList = (ArrayList<Boat>) boatRepository.getAll();
        ArrayList<Boat> filteredBoatsList = new ArrayList<>();

        for (Boat boat : boatsList) {
            if (boat.getGuestLimit() >= filter.getPeople()) {
                // Date should overlap with boat availability
                boolean available = checkAvailability(filter.getStartDate(), filter.getEndDate(), boat.getId());
                if (available) {
                    filteredBoatsList.add(boat);
                }
            }
        }
        return filteredBoatsList;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void makeReservation(Long boatId, Reservation reservation) {

        try {
            //Ako pokusa ne daj da mijenja tj da doda reservation
            Boat boat = boatRepository.findLockedById(boatId);
            boat.getReservations().add(reservation);
            boatRepository.save(boat);
            updateAvailability(reservation.getStartDate(), reservation.getEndDate(), boatId);
            boatOwnerService.updatePoints(boat.getBoatOwner(), reservation.getPrice());
            earningsService.saveEarnings(reservation, boat.getBoatOwner().getEmail(), boat.getBoatOwner().getRank());
            updateAvailability(reservation.getStartDate(), reservation.getEndDate(), boatId);
            if (reservation.isOwnerCaptain()) {
                boatOwnerService.updateAvailability(reservation.getStartDate(), reservation.getEndDate(), boat.getBoatOwner().getEmail());
            }

        } catch (PessimisticLockingFailureException exception) {
            throw exception;

        }
    }

    private boolean dateOverlapsWithReservation(Collection<Reservation> reservations, Date startDate, Date endDate) {
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

    private boolean doPeriodsOverlap(Date startDate, Date endDate, Set<BoatAvailability> availableTimePeriods) {

        for (BoatAvailability period : availableTimePeriods) {
            if (dateService.doPeriodsOverlap(period.getStartDate(), period.getEndDate(), startDate, endDate)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<Boat> getAllByOwner(String email) {
        BoatOwner owner = boatOwnerRepository.findByEmail(email);
        if (owner == null) {
            throw new ResourceConflictException("Boat owner with email " + email + " does not exist");
        }
        return boatRepository.findAllByOwnerId(owner.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Boat getById(Long id) {
        return boatRepository.findById(id).orElseThrow(() -> new ResourceConflictException("Boat not found"));

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Boat found = boatRepository.findLockedById(id);
        if ( found == null ){
            throw new PessimisticLockingFailureException("Someone is already trying to reserve same boat at this moment!");
        }
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForBoat(id);
        if (!(noOfFutureRes > 0)) {
            found.setDeleted(true);
            boatRepository.save(found);
        }

    }

    @Override
    @Transactional
    public Boat addBoat(Boat boat, String ownerEmail) {
        BoatOwner owner = boatOwnerRepository.findByEmail(ownerEmail);
        boat.setBoatOwner(owner);
        return boatRepository.save(boat);
    }

    @Override
    @Transactional
    public void addImage(Long boatId, String fileName) {
        Boat found = boatRepository.findById(boatId)
                .orElseThrow(() -> new ResourceConflictException("Boat not found"));
        found.getImages().add(new Image(fileName));
        boatRepository.save(found);
    }

    @Override
    public Boat save(Boat boat) {
        return boatRepository.save(boat);
    }

    @Override
    @Transactional
    public Boat updateBoatInfo(Long id, BoatInfo updated) {
        Boat found = boatRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Boat not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVAJ BROD!
        //ako ima ne moze se editovati ako nema edituj ga
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForBoat(id);
        if (!(noOfFutureRes > 0)) {
            found.setName(updated.getName());
            found.setType(BoatType.valueOf(updated.getType()));
            found.setLength(updated.getLength());
            found.setEngineCount(updated.getEngineCount());
            found.setEnginePower(updated.getEnginePower());
            found.setMaxSpeed(updated.getMaxSpeed());
            found.setInformation(updated.getInformation());
            found.setDescription(updated.getDescription());
            found.setPricePerDay(updated.getPricePerDay());
            found.setCancelingPercentage(updated.getCancelingPercentage());
            found.setGuestLimit(updated.getGuestLimit());
        }
        return boatRepository.save(found);
    }

    @Override
    @Transactional
    public Boat updateBoatAdditionalInfo(Long id, BoatAdditionalInfo updated) {

        Boat found = boatRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Boat not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVAJ BROD!
        //ako ima ne moze se editovati ako nema edituj ga
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForBoat(id);
        if (!(noOfFutureRes > 0)) {
            found.setFishingEquipment(new HashSet<>(updated.getFishingEquipment()));
            found.setUtilities(new HashSet<>(updated.getUtilities()));
            found.setNavigationType(updated.getNavigationTypes().stream().map(NavigationType::valueOf).collect(Collectors.toSet()));
        }
        return boatRepository.save(found);
    }

    @Override
    @Transactional
    public Boat updateBoatRules(Long id, Collection<Rule> updated) {
        Boat found = boatRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Boat not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVAJ BROD!
        //ako ima ne moze se editovati ako nema edituj ga
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForBoat(id);
        if (!(noOfFutureRes > 0)) {
            found.setCodeOfConduct(new HashSet<>(updated));
        }
        return boatRepository.save(found);
    }

    @Override
    public Boat getBoatForReservation(Long reservationId) {
        return boatRepository.getBoatForReservation(reservationId);
    }

    @Override
    @Transactional
    public Boat updateBoatAddress(Long id, Address updated) {
        Boat found = boatRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Boat not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVAJ BROD!
        //ako ima ne moze se editovati ako nema edituj ga
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForBoat(id);
        if (!(noOfFutureRes > 0)) {
            found.setAddress(updated);
        }
        return boatRepository.save(found);
    }


    @Override
    @Transactional
    public Collection<BoatAvailability> addAvailabilityPeriod(BoatAvailability newAvailability, Long boatId) {
        Boat boat = boatRepository.findById(boatId).orElseThrow(() -> new ResourceConflictException("Boat not found"));
        //TODO:PROVERITI DA LI IMA REZ U TOM PERIODU
        Collection<Reservation> boatReservations = reservationService.getReservationForBoat(boatId);
        var foundOverlaps = boatReservations.stream().filter(reservation -> dateService.reservationOverlapsWithAvailability(reservation.getStartDate(), reservation.getEndDate(), newAvailability.getStartDate(), newAvailability.getEndDate())).collect(Collectors.toSet());
        if (foundOverlaps.size() > 0) {
            return boat.getAvailableTimePeriods();
        }
        Set<BoatAvailability> availabilities = new HashSet<>(boat.getAvailableTimePeriods());

        Optional<BoatAvailability> endOfNewPartOfSecond = availabilities.stream().filter(boatAvailability ->
                isBetween(newAvailability.getEndDate(), boatAvailability.getStartDate(), boatAvailability.getEndDate()) ||
                        newAvailability.getEndDate().equals(boatAvailability.getStartDate())).findFirst();
        Optional<BoatAvailability> startOfNewPartOfFirst = availabilities.stream().filter(boatAvailability ->
                isBetween(newAvailability.getStartDate(), boatAvailability.getStartDate(), boatAvailability.getEndDate()) ||
                        newAvailability.getStartDate().equals(boatAvailability.getEndDate())).findFirst();

        BoatAvailability missingPeriod = new BoatAvailability();
        missingPeriod.setStartDate(newAvailability.getStartDate());
        missingPeriod.setEndDate(newAvailability.getEndDate());


        if (endOfNewPartOfSecond.isPresent() && startOfNewPartOfFirst.isPresent()) {
            // novi zahvata pocetak jednog i kraj drugog
            if (!startOfNewPartOfFirst.equals(endOfNewPartOfSecond)) {
                //6  nadji one koji se mzd nalaze izmedju ova 2
                var mybInBetween = availabilities.stream().filter(boatAvailability ->
                        isBetween(boatAvailability.getStartDate(), startOfNewPartOfFirst.get().getEndDate(), endOfNewPartOfSecond.get().getStartDate()) &&
                                isBetween(boatAvailability.getEndDate(), startOfNewPartOfFirst.get().getEndDate(), endOfNewPartOfSecond.get().getStartDate())).collect(Collectors.toSet());

                //4 i 5 mybInBetween je potrebno izbrisati ako postoji  i kreirati samo jedan novi
                if (!mybInBetween.isEmpty()) {
                    missingPeriod.setStartDate(startOfNewPartOfFirst.get().getEndDate());
                    missingPeriod.setEndDate(endOfNewPartOfSecond.get().getStartDate());
                    //startOfNewPartOfFirst i endOfNewPartOfSecond brisemo
                    //ujesto njih kreiram novi koji kopicinje kad pocetak startOfNewPartOfFirst i zavrsava se kad kraj endOfNewPartOfSecond
                    BoatAvailability newOne = new BoatAvailability();
                    newOne.setStartDate(startOfNewPartOfFirst.get().getStartDate());
                    newOne.setEndDate(endOfNewPartOfSecond.get().getEndDate());
                    var saved = boatAvailabilityService.save(newOne);
                    availabilities.add(saved);

                    availabilities.remove(startOfNewPartOfFirst.get());
                    availabilities.remove(endOfNewPartOfSecond.get());
                    boatAvailabilityService.delete(startOfNewPartOfFirst.get().getId());
                    boatAvailabilityService.delete(endOfNewPartOfSecond.get().getId());

                    //OBRISI IH i ukloni iz broda
                    availabilities.removeAll(mybInBetween);
                    boatAvailabilityService.delete(mybInBetween);

                    boat.setAvailableTimePeriods(availabilities);
                    boatRepository.save(boat);
                } else {
                    //3 zahvata pocetak jednog a kraj drugog, pri tome ta dva su jedan pored drugog
                    missingPeriod.setStartDate(startOfNewPartOfFirst.get().getEndDate());
                    missingPeriod.setEndDate(endOfNewPartOfSecond.get().getStartDate());
                    //startOfNewPartOfFirst i endOfNewPartOfSecond brisemo
                    //ujesto njih kreiram novi koji kopicinje kad pocetak startOfNewPartOfFirst i zavrsava se kad kraj endOfNewPartOfSecond
                    BoatAvailability newOne = new BoatAvailability();
                    newOne.setStartDate(startOfNewPartOfFirst.get().getStartDate());
                    newOne.setEndDate(endOfNewPartOfSecond.get().getEndDate());
                    var saved = boatAvailabilityService.save(newOne);
                    availabilities.add(saved);

                    availabilities.remove(startOfNewPartOfFirst.get());
                    availabilities.remove(endOfNewPartOfSecond.get());
                    boatAvailabilityService.delete(startOfNewPartOfFirst.get().getId());
                    boatAvailabilityService.delete(endOfNewPartOfSecond.get().getId());

                    boat.setAvailableTimePeriods(availabilities);
                }

            } else {
                //7 taj period vec posoji i ne radi nista vec obradjeno
                return null;
            }
            //TODO:sacuvati sve ovo
            boatRepository.save(boat);
            return boat.getAvailableTimePeriods();

        }

//doraditi
        var entirelyInsideNewOne = availabilities.stream().filter(boatAvailability ->
                (isBetween(boatAvailability.getStartDate(), newAvailability.getStartDate(), newAvailability.getEndDate()) ||
                        boatAvailability.getStartDate().getTime() == (newAvailability.getStartDate().getTime())) &&
                        (isBetween(boatAvailability.getEndDate(), newAvailability.getStartDate(), newAvailability.getEndDate()) ||
                                boatAvailability.getEndDate().getTime() == (newAvailability.getEndDate().getTime()))).collect(Collectors.toSet());
        if (!entirelyInsideNewOne.isEmpty()) {

            //KREIRAJ NOVI
            BoatAvailability added = boatAvailabilityService.save(newAvailability);
            availabilities.add(added);
            //boat.setAvailableTimePeriods(new HashSet<>(checkForOverlapping(added, new ArrayList<>(availabilities))));

            //izbaci iz liste kod broda
            availabilities.removeAll(entirelyInsideNewOne);
            boat.setAvailableTimePeriods(availabilities);
            boatRepository.save(boat);
            //TODO:OBRISI IH SVE
            boatAvailabilityService.delete(entirelyInsideNewOne);
            //ovo ce na frontu da sjebe stvar vrv ali pri ponovnom ucitavanju trebalo bi da bude ok
            return boat.getAvailableTimePeriods();
        } else {
            Optional<BoatAvailability> endPartOfNew = availabilities.stream().filter(boatAvailability ->
                    isBetween(boatAvailability.getEndDate(), newAvailability.getStartDate(), newAvailability.getEndDate()) ||
                            boatAvailability.getEndDate().equals(newAvailability.getStartDate())).findFirst();
            Optional<BoatAvailability> startPartOfNew = availabilities.stream().filter(boatAvailability ->
                    isBetween(boatAvailability.getStartDate(), newAvailability.getStartDate(), newAvailability.getEndDate()) ||
                            boatAvailability.getStartDate().equals(newAvailability.getEndDate())).findFirst();

            if (!(endPartOfNew.isPresent() && startPartOfNew.isPresent())) {
                //1 novi zahvata kraj postojeceg
                if (endPartOfNew.isPresent()) {
                    missingPeriod.setStartDate(endPartOfNew.get().getStartDate());
                    endPartOfNew.get().setEndDate(newAvailability.getEndDate());
                    boatRepository.save(boat);
                    return boat.getAvailableTimePeriods();
                }
                //2 novi zahvata pocetak postojeceg
                if (startPartOfNew.isPresent()) {
                    missingPeriod.setEndDate(startPartOfNew.get().getEndDate());
                    startPartOfNew.get().setStartDate(newAvailability.getStartDate());
                    boatRepository.save(boat);
                    return boat.getAvailableTimePeriods();
                }


            } else {
                //ovdje zeleno 5
                var anyInBetween = availabilities.stream().filter(boatAvailability ->
                        isBetween(boatAvailability.getStartDate(), endPartOfNew.get().getEndDate(), startPartOfNew.get().getStartDate()) &&
                                isBetween(boatAvailability.getEndDate(), endPartOfNew.get().getEndDate(), startPartOfNew.get().getStartDate())).collect(Collectors.toSet());
                BoatAvailability newOne = new BoatAvailability();
                newOne.setStartDate(newAvailability.getStartDate());
                newOne.setEndDate(newAvailability.getEndDate());
                boatAvailabilityService.save(newOne);
                availabilities.add(newOne);

                availabilities.remove(endPartOfNew.get());
                availabilities.remove(startPartOfNew.get());
                boatAvailabilityService.delete(endPartOfNew.get().getId());
                boatAvailabilityService.delete(startPartOfNew.get().getId());


                if (!anyInBetween.isEmpty()) {
                    availabilities.remove(anyInBetween);
                    boatAvailabilityService.delete(anyInBetween);
                }
                boat.setAvailableTimePeriods(availabilities);
                boatRepository.save(boat);
                return boat.getAvailableTimePeriods();
            }
        }

        //u slucaju da nema nikakvih preklapanja samo ga dodaj
        BoatAvailability added = boatAvailabilityService.save(newAvailability);
        availabilities.add(added);
        boat.setAvailableTimePeriods(new HashSet<>(checkForOverlapping(added, new ArrayList<>(availabilities))));
        boatRepository.save(boat);
        return boat.getAvailableTimePeriods();

    }

    public boolean isBetween(Date theOne, Date start, Date end) {
        return theOne.after(start) && theOne.before(end);
    }


    private List<BoatAvailability> checkForOverlapping(BoatAvailability availability, List<BoatAvailability> availabilities) {

        List<BoatAvailability> retVal = new ArrayList<>();
        for (BoatAvailability a : availabilities) {
            if (!a.getId().equals(availability.getId()) && (isBetween(availability.getStartDate(), a) || isBetween(availability.getEndDate(), a))) {
                Date newStartDate = a.getStartDate();
                Date newEndDate = a.getEndDate();
                a = calculateNew(newStartDate, newEndDate, availability);
            } else if (availability.getStartDate().before(a.getStartDate()) &&
                    availability.getEndDate().after(a.getEndDate())) {
                a.setStartDate(availability.getStartDate());
                a.setEndDate(availability.getEndDate());
            }
            retVal.add(a);
        }

        return retVal;
    }

    private boolean isBetween(Date newAvailabilityDate, BoatAvailability availability) {
        return (newAvailabilityDate.after(availability.getStartDate())
                && newAvailabilityDate.before(availability.getEndDate())) ||
                newAvailabilityDate.equals(availability.getStartDate())
                || newAvailabilityDate.equals(availability.getEndDate());
    }

    private BoatAvailability calculateNew(Date newStartDate, Date newEndDate, BoatAvailability availability) {
        if (newStartDate.before(availability.getStartDate())) {
            availability.setStartDate(newStartDate);
        }
        if (newEndDate.after(availability.getEndDate())) {
            availability.setEndDate(newEndDate);
        }
        return availability;
    }


    @Transactional
    @Override
    public Collection<BoatAvailability> updateAvailability(Date reservationStartDate, Date reservationEndDate, Long id) {
        //TODO:
        Boat boat = boatRepository.findById(id).orElseThrow(() -> new ResourceConflictException("Boat not found"));
        Set<BoatAvailability> availabilities = new HashSet<>(boat.getAvailableTimePeriods());

        var availabilityInBetween = availabilities.stream().filter(boatAvailability ->
                isBetween(reservationStartDate, boatAvailability.getStartDate(), boatAvailability.getEndDate()) &&
                        isBetween(reservationEndDate, boatAvailability.getStartDate(), boatAvailability.getEndDate())).findFirst();
        var onStart = availabilities.stream().filter(boatAvailability ->
                reservationStartDate.getTime() == boatAvailability.getStartDate().getTime() &&
                        isBetween(reservationEndDate, boatAvailability.getStartDate(), boatAvailability.getEndDate())).findFirst();
        var onEnd = availabilities.stream().filter(boatAvailability ->
                reservationEndDate.getTime() == boatAvailability.getEndDate().getTime() &&
                        isBetween(reservationStartDate, boatAvailability.getStartDate(), boatAvailability.getEndDate())).findFirst();
        var theOne = availabilities.stream().filter(boatAvailability ->
                boatAvailability.getStartDate().getTime() == reservationStartDate.getTime() && boatAvailability.getEndDate().getTime() == reservationEndDate.getTime()).findFirst();

        if (theOne.isPresent()) {
            availabilities.remove(theOne.get());
            boat.setAvailableTimePeriods(availabilities);
            boatRepository.save(boat);
            boatAvailabilityService.delete(theOne.get().getId());
            return boat.getAvailableTimePeriods();
        } else if (onStart.isPresent()) {
            BoatAvailability newOne = new BoatAvailability();
            newOne.setStartDate(DateUtil.addDays(reservationEndDate, 1));
            newOne.setEndDate(onStart.get().getEndDate());
            var saved = boatAvailabilityService.save(newOne);
            availabilities.add(saved);
            availabilities.remove(onStart.get());
            boat.setAvailableTimePeriods(availabilities);
            boatRepository.save(boat);
            boatAvailabilityService.delete(onStart.get().getId());
            return boat.getAvailableTimePeriods();
        } else if (onEnd.isPresent()) {
            BoatAvailability newOne = new BoatAvailability();
            newOne.setStartDate(onEnd.get().getStartDate());
            newOne.setEndDate(DateUtil.addDays(reservationStartDate, -1));
            var saved = boatAvailabilityService.save(newOne);
            availabilities.add(saved);
            availabilities.remove(onEnd.get());
            boat.setAvailableTimePeriods(availabilities);
            boatRepository.save(boat);
            boatAvailabilityService.delete(onEnd.get().getId());
            return boat.getAvailableTimePeriods();

        } else if (availabilityInBetween.isPresent()) {
            BoatAvailability newOne = new BoatAvailability();
            newOne.setStartDate(DateUtil.addDays(reservationEndDate, 1));
            newOne.setEndDate(availabilityInBetween.get().getEndDate());
            var saved = boatAvailabilityService.save(newOne);
            availabilities.add(saved);
            BoatAvailability anotherNew = new BoatAvailability();
            anotherNew.setStartDate(availabilityInBetween.get().getStartDate());
            anotherNew.setEndDate(DateUtil.addDays(reservationStartDate, -1));
            var anotherSaved = boatAvailabilityService.save(anotherNew);
            availabilities.add(anotherSaved);

            availabilities.remove(availabilityInBetween.get());
            boat.setAvailableTimePeriods(availabilities);
            boatRepository.save(boat);
            boatAvailabilityService.delete(availabilityInBetween.get().getId());
            return boat.getAvailableTimePeriods();
        }
        return boat.getAvailableTimePeriods();
    }

    @Override
    public boolean checkAvailability(Date from, Date to, Long boatId) {
        boolean isAvailable = false;
        Boat boat = boatRepository.findById(boatId).orElseThrow(() -> new ResourceConflictException("Boat not found"));
        List<BoatAvailability> availabilityPeriods = new ArrayList<>(boat.getAvailableTimePeriods());

        for (BoatAvailability period : availabilityPeriods) {
            if ((from.after(period.getStartDate()) || from.equals(period.getStartDate())) && (to.before(period.getEndDate()) || to.equals(period.getEndDate()))) {
                isAvailable = true;
                break;
            }
        }
        return isAvailable;
    }

    @Override
    public int getNoOfIncomingReservations(Long id) {
        return reservationService.getNoOfIncomingReservationsForBoat(id);
    }

    @Override
    public Boolean checkIfReservationOverlapsAvailability(BoatAvailability newAvailability, Long boatId) {
        Boat boat = boatRepository.findById(boatId).orElseThrow(() -> new ResourceConflictException("Boat not found"));
        //TODO:PROVERITI DA LI IMA REZ U TOM PERIODU
        Collection<Reservation> boatReservations = reservationService.getReservationForBoat(boatId);
        var foundOverlaps = boatReservations.stream().filter(reservation -> dateService.reservationOverlapsWithAvailability(reservation.getStartDate(), reservation.getEndDate(), newAvailability.getStartDate(), newAvailability.getEndDate())).collect(Collectors.toSet());
        if (foundOverlaps.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void updateBoatRating(Long id, double boatRating) {
        Boat boat = boatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Boat not found"));
        boat.setRating(boatRating);
        boatRepository.save(boat);
    }

    @Override
    public Boat findLockedById(Long id){
        return boatRepository.findLockedById(id);
    }
}
