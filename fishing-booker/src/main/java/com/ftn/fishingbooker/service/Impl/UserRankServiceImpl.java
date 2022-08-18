package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.UserRank;
import com.ftn.fishingbooker.repository.UserRankRepository;
import com.ftn.fishingbooker.service.UserRankService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserRankServiceImpl implements UserRankService {

    private final UserRankRepository userRankRepository;

    public UserRankServiceImpl(UserRankRepository userRankRepository) {
        this.userRankRepository = userRankRepository;
    }

    @Override
    public Collection<UserRank> getLoyaltyProgram() {
        return userRankRepository.findAll();
    }

    @Override
    public Collection<UserRank> saveLoyaltyProgram(Collection<UserRank> ranks) {
        return userRankRepository.saveAll(ranks);
    }
}
