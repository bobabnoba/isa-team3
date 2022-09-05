package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.mapper.EarningsMapper;
import com.ftn.fishingbooker.model.Earnings;
import com.ftn.fishingbooker.service.EarningsService;
import com.ftn.fishingbooker.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/earnings")
public class EarningsController {

    private final EarningsService  earningsService;

    public EarningsController(EarningsService earningsService) {
        this.earningsService = earningsService;
    }

    @GetMapping
    public ResponseEntity<Collection<EarningsDto>> getAllForAdvertiserInDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to){
        Collection<Earnings> found = earningsService.getAllInDateRange(from, to);
        Collection<EarningsDto> dtos = found.stream()
                .map(EarningsMapper::toDto)
                .collect(Collectors.toList());
        return ok(dtos);
    }


    @GetMapping("/advertiser/{email}")
    public ResponseEntity<Collection<EarningsDto>> getAllForAdvertiserInDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
            @PathVariable String email) {
        Collection<Earnings> found = earningsService.getAllForAdvertiserInDateRange(from, to, email);
        Collection<EarningsDto> dtos = found.stream()
                .map(EarningsMapper::toDto)
                .collect(Collectors.toList());
        return ok(dtos);
    }

    @GetMapping("/advertiser-chart/{email}")
    public ResponseEntity<Collection<EarningsChartDto>> getAllForAdvertiserChartInDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
            @PathVariable String email) {
        Collection<Earnings> found = earningsService.getAllForAdvertiserInDateRange(from, to, email);
        Collection<EarningsChartDto> earningsDtos = new ArrayList<>();
        for (Date d = from; d.before(to); d = DateUtil.addDays(d,1)){
            Date finalD = d;
            AtomicReference<Double> money = new AtomicReference<>(0.0);
            Collection<Earnings> dtoDay = found.stream().filter(earnings -> {
                if(earnings.getTransactionDate().after(finalD) &&
                        earnings.getTransactionDate().before(DateUtil.addDays(finalD,1))){
                    money.set(money.get() + earnings.getAmount());
                }
                return false;
            }).collect(Collectors.toSet());
            EarningsChartDto dto = new EarningsChartDto();
            dto.setDate(finalD);
            dto.setIncome(money.get());
            earningsDtos.add(dto);
        }
        return ok(earningsDtos);
    }

}
