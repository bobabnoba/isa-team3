package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.*;

public interface HomeOwnerService {
    User registerHomeOwner(HomeOwner mapToHomeOwner, String motivation);
}
