package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.model.InstructorAvailability;
import com.ftn.fishingbooker.model.SpecialOffer;
import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.repository.SpecialOfferRepository;
import com.ftn.fishingbooker.service.AdventureService;
import com.ftn.fishingbooker.service.InstructorService;
import com.ftn.fishingbooker.service.SpecialOfferService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SpecialOfferServiceImpl implements SpecialOfferService {

    private final AdventureService adventureService;
    private final SpecialOfferRepository specialOfferRepository;
    private final InstructorService instructorService;

    public SpecialOfferServiceImpl(AdventureService adventureService, SpecialOfferRepository specialOfferRepository, InstructorService instructorService) {
        this.adventureService = adventureService;
        this.specialOfferRepository = specialOfferRepository;
        this.instructorService = instructorService;
    }

    @Override
    @Transactional
    public SpecialOffer createSpecialOffer(SpecialOffer specialOffer, Long serviceId) {
        SpecialOffer saved = specialOfferRepository.save(specialOffer);
        if(specialOffer.getType().equals(ReservationType.ADVENTURE)){
            Adventure adventure = adventureService.getById(serviceId);
            adventure.getSpecialOffers().add(saved);
            adventureService.save(adventure);

            String instructorMail = adventure.getInstructor().getEmail();
            instructorService.updateAvailability(new InstructorAvailability(saved.getReservationStartDate(), saved.getReservationEndDate()), instructorMail);
        }
        return saved;
    }
}
