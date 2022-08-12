package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Rule;
import com.ftn.fishingbooker.repository.RuleRepository;
import com.ftn.fishingbooker.service.RuleService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;

    public RuleServiceImpl(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public Collection<Rule> getAll() {
        return ruleRepository.findAll();
    }
}
