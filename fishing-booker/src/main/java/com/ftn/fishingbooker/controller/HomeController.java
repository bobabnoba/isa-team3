package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.AddressMapper;
import com.ftn.fishingbooker.mapper.RentalMapper;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.mapper.VacationHomeMapper;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.EmailService;
import com.ftn.fishingbooker.service.HomeService;
import com.ftn.fishingbooker.service.ReservationService;
import com.ftn.fishingbooker.util.FIleUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/vacation/homes")
@RequiredArgsConstructor
public class HomeController {
    private final HomeService vacationHomeService;
    private final ClientService clientService;
    private final ReservationService reservationService;
    private final EmailService emailService;

    @GetMapping("/{id}")
    public ResponseEntity<VacationHomeDto> getHomeById(@PathVariable Long id) {
        VacationHome found = vacationHomeService.getById(id);
        VacationHomeDto dto = VacationHomeMapper.map(found);
        return ok(dto);
    }

    @GetMapping()
    public Collection<RentalDto> GetAll() {
        Collection<VacationHome> homes = vacationHomeService.getAll();

        return RentalMapper.mapVacationHomeToRental(homes);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public Collection<VacationHomeDto> getAllVacations() {
        Collection<VacationHome> homes = vacationHomeService.getAll();

        return homes.stream()
                .map(VacationHomeMapper::mapToHomeOwnerDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/search")
    public ResponseEntity<Collection<RentalDto>> FilterAll(@RequestBody FilterDto filter) {
        if (clientService.hasOverlappingReservation(filter.getEmail(), filter.getStartDate(), filter.getEndDate())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Collection<VacationHome> vacationHomes = vacationHomeService.filterAll(filter);
        Collection<RentalDto> rentals = RentalMapper.mapVacationHomeToRental(vacationHomes);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @GetMapping("/reservations/{homeId}")
    public Collection<ReservationDto> GetVacationHomeReservations(@PathVariable Long homeId) {

        return ReservationMapper.map(reservationService.getReservationForVacationHome(homeId));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<VacationHomeDto> getHomeProfileById(@PathVariable Long id) {
        VacationHome found = vacationHomeService.getById(id);
        VacationHomeDto dto = VacationHomeMapper.mapToHomeOwnerDto(found);
        return ok(dto);
    }

//    @PostMapping("/rent/{homeId}/{userEmail}")
//    public ResponseEntity<ReservationDto> makeReservation(@PathVariable String userEmail, @PathVariable Long homeId, @RequestBody ReservationDto reservationDto) {
//        Client client = clientService.getClientByEmail(userEmail);
//        if (client.getNoOfPenalties() >= 3){
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//        reservationDto.setType(ReservationType.VACATION_HOME);
//        Reservation reservation = reservationService.makeReservation(client, reservationDto);
//        vacationHomeService.makeReservation(homeId, reservation);
//        clientService.updatePoints(client, reservation.getPrice());
//        emailService.sendReservationEmail(ReservationMapper.map(reservation), client);
//        return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);
//    }

    @PostMapping("/rent/{homeId}/{userEmail}")
    public ResponseEntity<ReservationDto> makeReservation(@PathVariable String userEmail, @PathVariable Long homeId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);
        if (client.getNoOfPenalties() >= 3) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        reservationDto.setType(ReservationType.VACATION_HOME);
        Reservation reservation = reservationService.makeVacationHomeReservation(client, homeId, reservationDto);
        if (reservation == null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);
        }
    }

    @PostMapping("/owner-rent/{homeId}/{userEmail}")
    public ResponseEntity<?> ownerMakeReservation(@PathVariable String userEmail, @PathVariable Long homeId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);
        if (client.getNoOfPenalties() >= 3) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try{
            Reservation reservation = reservationService.ownerMakeReservation(client, reservationDto, homeId);
            return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);
        }
        catch (PessimisticLockingFailureException e){
            return new ResponseEntity<>("Lock:" + e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/by-owner/{email}")
    public ResponseEntity<Collection<VacationHomeDto>> getAllHomesByOwner(@PathVariable String email) {
        Collection<VacationHome> found = vacationHomeService.getAllByOwner(email);
        Collection<VacationHomeDto> dtos = found.stream()
                .map(VacationHomeMapper::mapToHomeOwnerDto)
                .collect(Collectors.toList());

        return ok(dtos);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOME_OWNER')")
    public ResponseEntity<Void> deleteHome(@PathVariable Long id) {
        vacationHomeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<VacationHomeDto> addNewHome(@RequestBody NewHomeDto dto) {
        VacationHome home = VacationHomeMapper.toEntity(dto);
        VacationHome saved = vacationHomeService.addHome(home, dto.getOwnerEmail());
        return ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/info-update/{id}")
    public ResponseEntity<VacationHomeDto> updateHomeInfo(@PathVariable Long id, @RequestBody HomeInfoDto updated) {
        VacationHome saved = vacationHomeService.updateHomeInfo(id, updated);
        return ResponseEntity.ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/additional-update/{id}")
    public ResponseEntity<VacationHomeDto> updateHomeAdditionalInfo(@PathVariable Long id, @RequestBody HomeAdditionalInfo updated) {
        VacationHome saved = vacationHomeService.updateHomeAdditionalInfo(id, updated);
        return ResponseEntity.ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/code-of-conduct-update/{id}")
    public ResponseEntity<VacationHomeDto> updateHomeCodeOfConduct(@PathVariable Long id, @RequestBody Collection<Rule> updated) {
        VacationHome saved = vacationHomeService.updateHomeRules(id, updated);
        return ResponseEntity.ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/address-update/{id}")
    public ResponseEntity<VacationHomeDto> updateHomeAddress(@PathVariable Long id, @RequestBody AddressDto updated) {
        VacationHome saved = vacationHomeService.updateHomeAddress(id, AddressMapper.toEntity(updated));
        return ResponseEntity.ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/image-upload/{id}")
    public ResponseEntity<Object> uploadImages(@RequestParam MultipartFile file, @PathVariable Long id) throws IOException {

        String uploadDir = "images/homes/" + id;

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FIleUploadUtil.saveFile(uploadDir, fileName, file);
        vacationHomeService.addImage(id, fileName);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-availability")
    public ResponseEntity<Collection<HomeAvailabilityDto>> addAvailabilityPeriod(@RequestBody HomeAvailabilityRequestDto availability) {
        Collection<VacationHomeAvailability> availabilities = vacationHomeService.addAvailabilityPeriod(VacationHomeMapper.mapToHomeAvailabilityEntity(availability), availability.getHomeId());
        Collection<HomeAvailabilityDto> dtos = availabilities.stream()
                .map(VacationHomeMapper::mapToAvailabilityDto)
                .collect(Collectors.toList());
        return ok(dtos);
    }

    @GetMapping("/check-if-available")
    ResponseEntity<Boolean> checkAvailability(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date to,
                                              @RequestParam Long homeId) {
        return ok(vacationHomeService.checkAvailability(from, to, homeId));
    }

    @GetMapping("for-reservation/{reservationId}")
    public ResponseEntity<HomeInfoDto> getHomeForReservation(@PathVariable Long reservationId) {
        VacationHome home = vacationHomeService.getHomeForReservation(reservationId);
        return new ResponseEntity<>(VacationHomeMapper.mapToDtoInfo(home), HttpStatus.OK);
    }

    @GetMapping("{id}/has-incoming-reservations")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOME_OWNER')")
    public ResponseEntity<Boolean> adventureHasIncomingReservations(@PathVariable Long id){
        return ResponseEntity.ok(vacationHomeService.getNoOfIncomingReservations(id) > 0);
    }

    @PostMapping("/check-if-res-overlaps-avail")
    public ResponseEntity<Boolean> checkIfReservationOverlapsAvailability(@RequestBody HomeAvailabilityRequestDto availability) {
        return ok(vacationHomeService.checkIfReservationOverlapsAvailability(VacationHomeMapper.mapToHomeAvailabilityEntity(availability), availability.getHomeId()));

    }

    @PostMapping("/remove-availability")
    public ResponseEntity<Collection<HomeAvailabilityDto>> deleteAvailabilityPeriod(@RequestBody HomeAvailabilityRequestDto availability) {
        Collection<VacationHomeAvailability> availabilities = vacationHomeService.updateAvailability(availability.getStartDate(), availability.getEndDate(), availability.homeId);
        Collection<HomeAvailabilityDto> dtos = availabilities.stream()
                .map(VacationHomeMapper::mapToAvailabilityDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}

