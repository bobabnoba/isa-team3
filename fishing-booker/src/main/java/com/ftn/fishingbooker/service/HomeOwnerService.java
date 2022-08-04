package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.HomeOwner;
import com.ftn.fishingbooker.model.User;

public interface HomeOwnerService {

    User register(HomeOwner homeOwner, String motivation) throws ResourceConflictException;
}
