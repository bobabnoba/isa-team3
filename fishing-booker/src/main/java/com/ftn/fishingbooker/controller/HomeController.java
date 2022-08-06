package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.VacationHomeDto;
import com.ftn.fishingbooker.mapper.VacationHomeMapper;
import com.ftn.fishingbooker.model.VacationHome;
import com.ftn.fishingbooker.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacation/homes")
@RequiredArgsConstructor
public class HomeController {
    private final HomeService vacationHomeService;

    @GetMapping("/{id}")
    public VacationHomeDto GetVacationHome(@PathVariable("id") Long id) {
        VacationHome home = vacationHomeService.getById(id);

        return VacationHomeMapper.map(home);
    }
}