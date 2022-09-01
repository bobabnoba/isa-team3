package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dao.SpecialOfferCalendarInfo;
import com.ftn.fishingbooker.model.SpecialOffer;

import javax.mail.MessagingException;
import java.util.Collection;

public interface SpecialOfferService {

    SpecialOffer createSpecialOffer(SpecialOffer specialOffer, Long serviceId)  throws MessagingException;

    Collection<SpecialOffer> getAvailableOffersForAdventure(Long adventureId);

    void reserveSpecialOffer(Long offerId);

    Collection<SpecialOfferCalendarInfo> getAllInstructorsOffers(Long id);
}
