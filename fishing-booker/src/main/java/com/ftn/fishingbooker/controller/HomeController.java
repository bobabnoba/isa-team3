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
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public Collection<RentalDto> GetAll() {
        Collection<VacationHome> homes = vacationHomeService.getAll();

        return RentalMapper.mapVacationHomeToRental(homes);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN', 'HOME_OWNER')")
    public Collection<VacationHomeDto> getAllVacations() {
        Collection<VacationHome> homes = vacationHomeService.getAll();

        return homes.stream()
                .map(VacationHomeMapper::mapToHomeOwnerDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public ResponseEntity<Collection<RentalDto>> FilterAll(@RequestBody FilterDto filter) {
        if (clientService.hasOverlappingReservation(filter.getEmail(), filter.getStartDate(), filter.getEndDate())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Collection<VacationHome> vacationHomes = vacationHomeService.filterAll(filter);
        Collection<RentalDto> rentals = RentalMapper.mapVacationHomeToRental(vacationHomes);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @GetMapping("/reservations/{homeId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public Collection<ReservationDto> GetVacationHomeReservations(@PathVariable Long homeId) {

        return ReservationMapper.map(reservationService.getReservationForVacationHome(homeId));
    }

    @GetMapping("/profile/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public ResponseEntity<VacationHomeDto> getHomeProfileById(@PathVariable Long id) {
        VacationHome found = vacationHomeService.getById(id);
        VacationHomeDto dto = VacationHomeMapper.mapToHomeOwnerDto(found);
        return ok(dto);
    }

    @PostMapping("/rent/{homeId}/{userEmail}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public ResponseEntity<ReservationDto> makeReservation(@PathVariable String userEmail, @PathVariable Long homeId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);
        if (client.getNoOfPenalties() >= 3) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (clientService.hasOverlappingReservation(userEmail, reservationDto.getStartDate(), reservationDto.getEndDate())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        reservationDto.setType(ReservationType.VACATION_HOME);
        Reservation reservation = reservationService.makeVacationHomeReservation(client, homeId, reservationDto);

        emailService.sendReservationEmail(ReservationMapper.map(reservation), client);
        return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);

    }

    @PostMapping("/owner-rent/{homeId}/{userEmail}")
    @PreAuthorize("hasRole('HOME_OWNER')")
    public ResponseEntity<?> ownerMakeReservation(@PathVariable String userEmail, @PathVariable Long homeId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);
        if (client.getNoOfPenalties() >= 3) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Reservation reservation = reservationService.ownerMakeReservation(client, reservationDto, homeId);
        return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);

    }

    @GetMapping("/by-owner/{email}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public ResponseEntity<Collection<VacationHomeDto>> getAllHomesByOwner(@PathVariable String email) {
        Collection<VacationHome> found = vacationHomeService.getAllByOwner(email);
        Collection<VacationHomeDto> dtos = found.stream()
                .map(VacationHomeMapper::mapToHomeOwnerDto)
                .collect(Collectors.toList());

        return ok(dtos);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOME_OWNER')")
    public ResponseEntity<?> deleteHome(@PathVariable Long id) {

        try{
            vacationHomeService.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        catch (PessimisticLockingFailureException e){
            return new ResponseEntity<>("Lock:" + e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasRole('HOME_OWNER')")
    public ResponseEntity<VacationHomeDto> addNewHome(@RequestBody NewHomeDto dto) {
        VacationHome home = VacationHomeMapper.toEntity(dto);
        VacationHome saved = vacationHomeService.addHome(home, dto.getOwnerEmail());
        return ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/info-update/{id}")
    @PreAuthorize("hasRole('HOME_OWNER')")
    public ResponseEntity<VacationHomeDto> updateHomeInfo(@PathVariable Long id, @RequestBody HomeInfoDto updated) {
        VacationHome saved = vacationHomeService.updateHomeInfo(id, updated);
        return ResponseEntity.ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/additional-update/{id}")
    @PreAuthorize("hasRole('HOME_OWNER')")
    public ResponseEntity<VacationHomeDto> updateHomeAdditionalInfo(@PathVariable Long id, @RequestBody HomeAdditionalInfo updated) {
        VacationHome saved = vacationHomeService.updateHomeAdditionalInfo(id, updated);
        return ResponseEntity.ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/code-of-conduct-update/{id}")
    @PreAuthorize("hasRole('HOME_OWNER')")
    public ResponseEntity<VacationHomeDto> updateHomeCodeOfConduct(@PathVariable Long id, @RequestBody Collection<Rule> updated) {
        VacationHome saved = vacationHomeService.updateHomeRules(id, updated);
        return ResponseEntity.ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/address-update/{id}")
    @PreAuthorize("hasRole('HOME_OWNER')")
    public ResponseEntity<VacationHomeDto> updateHomeAddress(@PathVariable Long id, @RequestBody AddressDto updated) {
        VacationHome saved = vacationHomeService.updateHomeAddress(id, AddressMapper.toEntity(updated));
        return ResponseEntity.ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/image-upload/{id}")
    @PreAuthorize("hasRole('HOME_OWNER')")
    public ResponseEntity<Object> uploadImages(@RequestParam MultipartFile file, @PathVariable Long id) throws IOException {

        String uploadDir = "images/homes/" + id;

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FIleUploadUtil.saveFile(uploadDir, fileName, file);
        vacationHomeService.addImage(id, fileName);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-availability")
    @PreAuthorize("hasRole('HOME_OWNER')")
    public ResponseEntity<Collection<HomeAvailabilityDto>> addAvailabilityPeriod(@RequestBody HomeAvailabilityRequestDto availability) {
        Collection<VacationHomeAvailability> availabilities = vacationHomeService.addAvailabilityPeriod(VacationHomeMapper.mapToHomeAvailabilityEntity(availability), availability.getHomeId());
        Collection<HomeAvailabilityDto> dtos = availabilities.stream()
                .map(VacationHomeMapper::mapToAvailabilityDto)
                .collect(Collectors.toList());
        return ok(dtos);
    }

    @GetMapping("/check-if-available")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    ResponseEntity<Boolean> checkAvailability(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date to,
                                              @RequestParam Long homeId) {
        return ok(vacationHomeService.checkAvailability(from, to, homeId));
    }

    @GetMapping("for-reservation/{reservationId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public ResponseEntity<Boolean> checkIfReservationOverlapsAvailability(@RequestBody HomeAvailabilityRequestDto availability) {
        return ok(vacationHomeService.checkIfReservationOverlapsAvailability(VacationHomeMapper.mapToHomeAvailabilityEntity(availability), availability.getHomeId()));

    }

    @PostMapping("/remove-availability")
    @PreAuthorize("hasRole('HOME_OWNER')")
    public ResponseEntity<Collection<HomeAvailabilityDto>> deleteAvailabilityPeriod(@RequestBody HomeAvailabilityRequestDto availability) {
        Collection<VacationHomeAvailability> availabilities = vacationHomeService.updateAvailability(availability.getStartDate(), availability.getEndDate(), availability.homeId);
        Collection<HomeAvailabilityDto> dtos = availabilities.stream()
                .map(VacationHomeMapper::mapToAvailabilityDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}

