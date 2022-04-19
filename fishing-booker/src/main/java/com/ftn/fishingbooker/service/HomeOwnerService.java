package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.*;

import javax.mail.*;

public interface HomeOwnerService {
    User registerHomeOwner(HomeOwner mapToHomeOwner, String motivation);
}
