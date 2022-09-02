package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.ClientReview;
import java.util.Collection;

public interface ReviewService {

    ClientReview makeAdventureReview(ClientReview clientReview);

    ClientReview makeBoatReview(ClientReview clientReview);

    ClientReview makeVacationHomeReview(ClientReview clientReview);

    Boolean checkForReview(Long reservationId);

    Collection<ClientReview> getAllPendingReviews();

    void handleReview(Long id, Boolean approved);
}
