package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.home.VacationHome;

import java.util.ArrayList;

public interface VacationHomeService {
    ArrayList<VacationHome> getAll();

    VacationHome getById(Long id);
}
