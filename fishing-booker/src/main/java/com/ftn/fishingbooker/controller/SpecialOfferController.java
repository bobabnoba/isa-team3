package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.NewSpecialOfferDto;
import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.dto.SpecialOfferDto;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.mapper.SpecialOfferMapper;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.Date;


@RestController
@RequestMapping("/special-offers")
@RequiredArgsConstructor
public class SpecialOfferController {

    private final SpecialOfferService specialOfferService;
    private final ClientService clientService;
    private final AdventureService adventureService;
    private final HomeService homeService;
    private final BoatService boatService;
    private final DateService dateService;
    private final ReservationService reservationService;
    private final EmailService emailService;
    private final InstructorService instructorService;

    @PostMapping("/{serviceId}")
    public ResponseEntity<SpecialOfferDto> createSpecialOffer(@RequestBody NewSpecialOfferDto specialOffer,
                                                              @PathVariable Long serviceId) throws MessagingException {
        SpecialOffer offer = SpecialOfferMapper.toNewEntity(specialOffer);
        SpecialOffer created = specialOfferService.createSpecialOffer(offer, serviceId);
        return ResponseEntity.ok(SpecialOfferMapper.toDto(created));
    }

    @GetMapping("/adventure/{adventureId}")
    public ResponseEntity<Collection<SpecialOfferDto>> getAvailableOffersForAdventure(@PathVariable Long adventureId) {
        Collection<SpecialOffer> found = specialOfferService.getAvailableOffersForAdventure(adventureId);
        return ResponseEntity.ok(SpecialOfferMapper.toDto(found));
    }

    @PostMapping("/rent/{type}/{rentalId}/{offerId}/{userEmail}")
    public ResponseEntity<ReservationDto> makeSpecialOfferReservation(@PathVariable String type, @PathVariable String userEmail, @PathVariable Long offerId, @PathVariable Long rentalId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);

        if (client.getNoOfPenalties() >= 3) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (type.equals("adventure")) {
            Adventure adventure = adventureService.getById(rentalId);
            Date newEndDate = dateService.addHoursToJavaUtilDate(reservationDto.getStartDate(), adventure.getDurationInHours());
            reservationDto.setEndDate(newEndDate);

        }
        if (clientService.hasOverlappingReservation(userEmail, reservationDto.getStartDate(), reservationDto.getEndDate())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        Reservation reservation = null;
        if (type.equals("adventure")) {
            reservationDto.setType(ReservationType.ADVENTURE);
            reservation = reservationService.makeSpecialOfferReservation(client, reservationDto);
            adventureService.makeReservation(rentalId, reservation);
            Adventure adventure = adventureService.getById(rentalId);
            instructorService.updateAvailability(new InstructorAvailability(reservation.getStartDate(), reservation.getEndDate()), adventure.getInstructor().getEmail());
            instructorService.updatePoints(adventure.getInstructor(), reservation.getPrice());

        } else if (type.equals("home")) {
            reservationDto.setType(ReservationType.VACATION_HOME);
            reservation = reservationService.makeSpecialOfferReservation(client, reservationDto);
            homeService.makeReservation(rentalId, reservation);

        } else if (type.equals("boat")) {
            reservationDto.setType(ReservationType.BOAT);
            reservation = reservationService.makeSpecialOfferReservation(client, reservationDto);
            boatService.makeReservation(rentalId, reservation);


        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        specialOfferService.reserveSpecialOffer(offerId);
        clientService.updatePoints(client, reservation.getPrice());
        emailService.sendReservationEmail(ReservationMapper.map(reservation), client);

        return new ResponseEntity<>(ReservationMapper.map(new Reservation()), HttpStatus.OK);
    }

}
