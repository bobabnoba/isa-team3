package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.ClientReview;

public interface ReviewService {

    ClientReview makeAdventureReview(ClientReview clientReview);

    ClientReview makeBoatReview(ClientReview clientReview);

    ClientReview makeVacationHomeReview(ClientReview clientReview);

    Boolean checkForReview(Long reservationId);
}
