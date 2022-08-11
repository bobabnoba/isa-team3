package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.FilterDto;
import com.ftn.fishingbooker.model.VacationHome;
import com.ftn.fishingbooker.repository.HomeRepository;
import com.ftn.fishingbooker.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final HomeRepository vacationHomeRepository;
    @Override
    public Collection<VacationHome> getAll() {
        return vacationHomeRepository.getAll();
    }

    @Override
    public VacationHome getById(Long id) {
        return vacationHomeRepository.getById(id);
    }

    @Override
    public Collection<VacationHome> filterAll(FilterDto filter) {
        return new ArrayList<>();
    }
}
