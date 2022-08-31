package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dao.SpecialOfferCalendarInfo;
import com.ftn.fishingbooker.model.SpecialOffer;

import java.util.Collection;

public interface SpecialOfferService {

    SpecialOffer createSpecialOffer(SpecialOffer specialOffer, Long serviceId);

    Collection<SpecialOffer> getAvailableOffersForAdventure(Long adventureId);

    void reserveSpecialOffer(Long offerId);

    Collection<SpecialOfferCalendarInfo> getAllInstructorsOffers(Long id);
}
