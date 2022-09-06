package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dao.SpecialOfferCalendarInfo;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.SpecialOfferRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Collection;

@Service
@Transactional
public class SpecialOfferServiceImpl implements SpecialOfferService {

    @Autowired
    @Lazy
    private  AdventureService adventureService;
    @Autowired
    private  SpecialOfferRepository specialOfferRepository;
    @Autowired
    private  InstructorService instructorService;
    @Autowired
    private  BoatService boatService;
    @Autowired
    private  BoatOwnerService boatOwnerService;
    @Autowired
    private  ClientService clientService;
    @Autowired
    private  HomeService homeService;

    @Override
    @Transactional
    public SpecialOffer createSpecialOffer(SpecialOffer specialOffer, Long serviceId) throws MessagingException {
        SpecialOffer saved = specialOfferRepository.save(specialOffer);
        if (specialOffer.getType().equals(ReservationType.ADVENTURE)) {
            Adventure adventure = adventureService.getById(serviceId);
            adventure.getSpecialOffers().add(saved);
            adventureService.save(adventure);
            clientService.emailSubscribers(adventure.getInstructor(), "instructor");
            String instructorMail = adventure.getInstructor().getEmail();
            instructorService.updateAvailability(new InstructorAvailability(saved.getReservationStartDate(), saved.getReservationEndDate()), instructorMail);
        }else if( specialOffer.getType().equals(ReservationType.BOAT)){
            Boat boat = boatService.findLockedById(serviceId);
            if ( boat == null ){
                throw new PessimisticLockingFailureException("Someone is already trying to reserve same boat at this moment!");
            }
            boat.getSpecialOffers().add(saved);
            boatService.save(boat);

            String ownerEmail = boat.getBoatOwner().getEmail();
            boatService.updateAvailability(saved.getReservationStartDate(), saved.getReservationEndDate(), boat.getId());

            if (specialOffer.isCaptain()) {
                boatOwnerService.updateAvailability(saved.getReservationStartDate(), saved.getReservationEndDate(), ownerEmail);
            }
            clientService.emailSubscribers(boat.getBoatOwner(), "boat");
        } else if(specialOffer.getType().equals(ReservationType.VACATION_HOME)){
            VacationHome home = homeService.findLockedById(serviceId);
            if ( home == null ){
                throw new PessimisticLockingFailureException("Someone is already trying to reserve same vacation home at this moment!");
            }
            home.getSpecialOffers().add(saved);
            homeService.save(home);

            homeService.updateAvailability(saved.getReservationStartDate(), saved.getReservationEndDate(), home.getId());
            clientService.emailSubscribers(home.getHomeOwner(), "home");
        }
        return saved;
    }

    @Override
    public Collection<SpecialOffer> getAvailableOffersForAdventure(Long adventureId) {
        return specialOfferRepository.getAvailableOffersForAdventure(adventureId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void reserveSpecialOffer(Long offerId) {
        try {
            SpecialOffer specialOffer = specialOfferRepository.findLockedById(offerId);
            specialOffer.setUsed(true);
            specialOfferRepository.save(specialOffer);
        } catch (Exception e) {
            System.out.println("Pessimistic lock: SpecialOffer");
            throw e;
        }
    }


    @Override
    public Collection<SpecialOfferCalendarInfo> getAllInstructorsOffers(Long id) {
        return specialOfferRepository.getAllOffersForInstructor(id);
    }

    @Override
    public Collection<SpecialOffer> getAllCaptainOffersForBoatOwner(Long id) {
        return specialOfferRepository.getAllCaptainOffersForBoatOwner(id);
    }

}
