package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.model.UserRank;
import com.ftn.fishingbooker.repository.UserRankRepository;
import com.ftn.fishingbooker.service.UserRankService;
import com.ftn.fishingbooker.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserRankServiceImpl implements UserRankService {

    private final UserRankRepository userRankRepository;
    private final UserService userService;

    public UserRankServiceImpl(UserRankRepository userRankRepository, UserService userService) {
        this.userRankRepository = userRankRepository;
        this.userService = userService;
    }

    @Override
    public Collection<UserRank> getLoyaltyProgram() {
        return userRankRepository.findAll();
    }

    @Override
    public Collection<UserRank> saveLoyaltyProgram(Collection<UserRank> ranks) {
        Collection<User> users = new ArrayList<>();

        for (User u : userService.findAllByDeleted(false)) {
            String userType;
            if( u.getRole().getName().equalsIgnoreCase("ROLE_CLIENT")) {
                userType = "CLIENT";
            } else {
                userType = "ADVERTISER";
            }
            for(var r : ranks.stream().sorted(Comparator.comparingDouble(UserRank::getMinPoints)).collect(Collectors.toList())) {
                if (r.getName().contains(userType) && r.getMinPoints() < u.getPoints() ){
                    u.setRank(r);
                }
            }
            users.add(u);
        }
        userService.saveAll(users);

        return userRankRepository.saveAll(ranks);
    }

    @Override
    public UserRank findByName(String name) {
        return userRankRepository.findByName(name);
    }

}
