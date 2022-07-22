package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class VacationHomeServiceImpl implements VacationHomeService {

    @Autowired
    private VacationHomeRepository vacationHomeRepository;

    @Override
    public VacationHome createVacationHome(NewVacationHomeDto newVacationHomeDto, HomeOwner owner) {
        VacationHome vacationHome = new VacationHome(
                newVacationHomeDto.name,
                newVacationHomeDto.address,
                newVacationHomeDto.description,
                null,
                newVacationHomeDto.codeOfConduct,
                null,
                newVacationHomeDto.information,
                owner
        );
        vacationHomeRepository.save(vacationHome);
        return vacationHome;
    }
}
