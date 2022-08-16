package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.SpecialOffer;

public interface SpecialOfferService {

    SpecialOffer createSpecialOffer(SpecialOffer specialOffer, Long serviceId);
}
