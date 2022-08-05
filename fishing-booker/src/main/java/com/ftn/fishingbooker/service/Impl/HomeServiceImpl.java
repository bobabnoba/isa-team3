package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.VacationHome;
import com.ftn.fishingbooker.repository.HomeRepository;
import com.ftn.fishingbooker.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final HomeRepository vacationHomeRepository;
    @Override
    public Collection<VacationHome> getAll() {
        return null;
    }

    @Override
    public VacationHome getById(Long id) {
        return vacationHomeRepository.getById(id);
    }
}
