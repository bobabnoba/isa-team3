package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.model.Rule;
import com.ftn.fishingbooker.service.RuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/code-of-conduct")
public class RuleController {

    private final RuleService ruleService;

    public RuleController(RuleService ruleService) {

        this.ruleService = ruleService;
    }

    @GetMapping
    public ResponseEntity<Collection<Rule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAll());
    }
}
