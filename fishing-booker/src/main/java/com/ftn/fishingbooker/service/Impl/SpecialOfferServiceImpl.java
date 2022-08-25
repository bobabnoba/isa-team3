package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.SpecialOfferRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SpecialOfferServiceImpl implements SpecialOfferService {

    private final AdventureService adventureService;
    private final SpecialOfferRepository specialOfferRepository;
    private final InstructorService instructorService;
    private final BoatService boatService;
    public SpecialOfferServiceImpl(AdventureService adventureService, SpecialOfferRepository specialOfferRepository,
                                   InstructorService instructorService, BoatService boatService) {
        this.adventureService = adventureService;
        this.specialOfferRepository = specialOfferRepository;
        this.instructorService = instructorService;
        this.boatService = boatService;
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
        }else if( specialOffer.getType().equals(ReservationType.BOAT)){
            Boat boat = boatService.getById(serviceId);
            boat.getSpecialOffers().add(saved);
            boatService.save(boat);

            String ownerEmail = boat.getBoatOwner().getEmail();
            BoatAvailability unavailability = new BoatAvailability();
            boatService.updateAvailability(saved.getReservationStartDate(), saved.getReservationEndDate(), boat.getId());

            //TODO: u slucaju da je otkaceno da je vlasnik kapetan onda updateovati i njegov availability

        }
        return saved;
    }
}
