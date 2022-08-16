package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.Utility;
import java.util.Collection;

public interface UtilityService {
    Utility getByName(String name);
    public Collection<Utility> getAll();
}
