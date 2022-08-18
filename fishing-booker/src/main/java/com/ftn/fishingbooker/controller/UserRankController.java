package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.model.UserRank;
import com.ftn.fishingbooker.service.UserRankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/user-rank")
public class UserRankController {

    private final UserRankService userRankService;

    public UserRankController(UserRankService userRankService) {
        this.userRankService = userRankService;
    }

    @GetMapping
    public ResponseEntity<Collection<UserRank>> getLoyaltyProgram(){
        return ResponseEntity.ok(userRankService.getLoyaltyProgram());
    }
}
