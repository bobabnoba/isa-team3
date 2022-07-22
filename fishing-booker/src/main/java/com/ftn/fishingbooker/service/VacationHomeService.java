package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.*;

public interface VacationHomeService {

    VacationHome createVacationHome(NewVacationHomeDto newVacationHomeDto,HomeOwner owner);
}
