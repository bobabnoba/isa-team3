package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.home.VacationHome;
import com.ftn.fishingbooker.repository.VacationHomeRepository;
import com.ftn.fishingbooker.service.VacationHomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class VacationHomeServiceImpl implements VacationHomeService {
    private final VacationHomeRepository vacationHomeRepository;

    @Override
    public ArrayList<VacationHome> getAll() {
        return vacationHomeRepository.getAll();
    }

    @Override
    public VacationHome getById(Long id) {
        return vacationHomeRepository.getById(id);
    }
}
