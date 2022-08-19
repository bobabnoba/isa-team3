package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.UserRankDto;
import com.ftn.fishingbooker.mapper.UserRankMapper;
import com.ftn.fishingbooker.model.UserRank;
import com.ftn.fishingbooker.service.UserRankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-rank")
public class UserRankController {

    private final UserRankService userRankService;

    public UserRankController(UserRankService userRankService) {
        this.userRankService = userRankService;
    }

    @GetMapping
    public ResponseEntity<Collection<UserRankDto>> getLoyaltyProgram(){

        Collection<UserRank> found = userRankService.getLoyaltyProgram();

        Collection<UserRankDto> dtos = found.stream()
                .map(UserRankMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<Collection<UserRankDto>> updateLoyaltyProgram(@RequestBody Collection<UserRankDto> program){

        Collection<UserRank> userRanks = program.stream()
                .map(UserRankMapper::toEntity)
                .collect(Collectors.toList());

        Collection<UserRank> saved = userRankService.saveLoyaltyProgram(userRanks);

        Collection<UserRankDto> dtos = saved.stream()
                .map(UserRankMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
