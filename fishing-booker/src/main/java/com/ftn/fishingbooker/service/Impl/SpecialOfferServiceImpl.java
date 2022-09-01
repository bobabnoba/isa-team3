package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.repository.SpecialOfferRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class SpecialOfferServiceImpl implements SpecialOfferService {

    private final AdventureService adventureService;
    private final SpecialOfferRepository specialOfferRepository;
    private final InstructorService instructorService;
    private final BoatService boatService;
    private final BoatOwnerService boatOwnerService;
    private final HomeService homeService;
    public SpecialOfferServiceImpl(AdventureService adventureService, SpecialOfferRepository specialOfferRepository,
                                   InstructorService instructorService, BoatService boatService, BoatOwnerService boatOwnerService,
                                   HomeService homeService) {
        this.adventureService = adventureService;
        this.specialOfferRepository = specialOfferRepository;
        this.instructorService = instructorService;
        this.boatService = boatService;
        this.boatOwnerService = boatOwnerService;
        this.homeService = homeService;
    }

    @Override
    @Transactional
    public SpecialOffer createSpecialOffer(SpecialOffer specialOffer, Long serviceId) {
        SpecialOffer saved = specialOfferRepository.save(specialOffer);
        if (specialOffer.getType().equals(ReservationType.ADVENTURE)) {
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
            boatService.updateAvailability(saved.getReservationStartDate(), saved.getReservationEndDate(), boat.getId());

             if(specialOffer.isCaptain()){
                boatOwnerService.updateAvailability(saved.getReservationStartDate(),saved.getReservationEndDate(), ownerEmail);
            }
        } else if(specialOffer.getType().equals(ReservationType.VACATION_HOME)){
            VacationHome home = homeService.getById(serviceId);
            home.getSpecialOffers().add(saved);
            homeService.save(home);

            homeService.updateAvailability(saved.getReservationStartDate(), saved.getReservationEndDate(), home.getId());

        }
        return saved;
    }

    @Override
    public Collection<SpecialOffer> getAvailableOffersForAdventure(Long adventureId) {
        return specialOfferRepository.getAvailableOffersForAdventure(adventureId);
    }

    @Override
    public void reserveSpecialOffer(Long offerId) {
        SpecialOffer specialOffer = specialOfferRepository.findById(offerId).orElseThrow(() -> new ResourceConflictException("Offer not found"));
        specialOffer.setUsed(true);
        specialOfferRepository.save(specialOffer);
    }
}
