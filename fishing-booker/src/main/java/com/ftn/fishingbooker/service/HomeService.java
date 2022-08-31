package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.*;

import java.util.Collection;

public interface HomeService {
    Collection<VacationHome> getAll();

    VacationHome getById(Long id);

    Collection<VacationHome> filterAll(FilterDto filter);

    void makeReservation(Long homeId, Reservation reservation);

    VacationHome getVacationHomeForReservation(Long reservationId);

    Collection<VacationHome> getAllByOwner(String email);

    void deleteById(Long id);

    VacationHome addHome(VacationHome home, String ownerEmail);

    VacationHome updateHomeInfo(Long id, HomeInfoDto updated);

    VacationHome updateHomeAdditionalInfo(Long id, HomeAdditionalInfo updated);

    VacationHome updateHomeRules(Long id, Collection<Rule> updated);

    VacationHome updateHomeAddress(Long id, Address updated);

    void addImage(Long boatId, String fileName);



}
