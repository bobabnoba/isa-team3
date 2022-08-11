package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.FilterDto;
import com.ftn.fishingbooker.dto.RentalDto;
import com.ftn.fishingbooker.dto.VacationHomeDto;
import com.ftn.fishingbooker.mapper.RentalMapper;
import com.ftn.fishingbooker.mapper.VacationHomeMapper;
import com.ftn.fishingbooker.model.VacationHome;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/vacation/homes")
@RequiredArgsConstructor
public class HomeController {
    private final HomeService vacationHomeService;
    private final ClientService clientService;

    @GetMapping("/{id}")
    public VacationHomeDto GetVacationHome(@PathVariable("id") Long id) {
        VacationHome home = vacationHomeService.getById(id);

        return VacationHomeMapper.map(home);
    }

    @GetMapping()
    public Collection<RentalDto> GetAll() {
        Collection<VacationHome> homes = vacationHomeService.getAll();

        return RentalMapper.mapVacationHomeToRental(homes);
    }

    @PostMapping("/search/{clientId}")
    public ResponseEntity<Collection<RentalDto>> FilterAll( @PathVariable Long clientId, @RequestBody FilterDto filter) {
        if (clientService.hasOverlappingReservation(clientId, filter.getStartDate(), filter.getEndDate())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Collection<VacationHome> vacationHomes = vacationHomeService.filterAll(filter);
        Collection<RentalDto> rentals = RentalMapper.mapVacationHomeToRental(vacationHomes);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }
}
