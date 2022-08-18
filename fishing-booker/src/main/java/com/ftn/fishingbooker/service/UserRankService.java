package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.UserRank;

import java.util.Collection;

public interface UserRankService {

    Collection<UserRank> getLoyaltyProgram();
    Collection<UserRank> saveLoyaltyProgram(Collection<UserRank> ranks);
}
