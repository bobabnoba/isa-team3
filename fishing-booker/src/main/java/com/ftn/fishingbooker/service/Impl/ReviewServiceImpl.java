package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.enumeration.ReviewStatus;
import com.ftn.fishingbooker.exception.EntityNotFoundException;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.model.Boat;
import com.ftn.fishingbooker.model.ClientReview;
import com.ftn.fishingbooker.model.VacationHome;
import com.ftn.fishingbooker.repository.ReviewRepository;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AdventureService adventureService;
    private final UserServiceImpl userService;
    private final BoatService boatService;
    private final HomeService homeService;
    private final EmailService emailService;
    protected final Log loggerLog = LogFactory.getLog(getClass());


    @Override
    public Boolean checkForReview(Long reservationId) {
        ClientReview review = reviewRepository.findByReservationId(reservationId);
        return review != null;
    }

    @Override
    public ClientReview makeAdventureReview(ClientReview clientReview) {
        Adventure adventure = adventureService.getAdventureForReservation(clientReview.getReservationId());
        clientReview.setRentalId(adventure.getId());
        clientReview.setRentalName(adventure.getTitle());
        String ownerEmail = userService.getUserById(adventure.getInstructor().getId()).getEmail();
        clientReview.setOwnerEmail(ownerEmail);

        return reviewRepository.save(clientReview);
    }

    @Override
    public ClientReview makeBoatReview(ClientReview clientReview) {
        Boat boat = boatService.getBoatForReservation(clientReview.getReservationId());
        clientReview.setRentalId(boat.getId());
        clientReview.setRentalName(boat.getName());
        String ownerEmail = userService.getUserById(boat.getBoatOwner().getId()).getEmail();
        clientReview.setOwnerEmail(ownerEmail);

        return reviewRepository.save(clientReview);
    }

    @Override
    public ClientReview makeVacationHomeReview(ClientReview clientReview) {
        VacationHome vacationHome = homeService.getVacationHomeForReservation(clientReview.getReservationId());
        clientReview.setRentalId(vacationHome.getId());
        clientReview.setRentalName(vacationHome.getName());
        String ownerEmail = userService.getUserById(vacationHome.getHomeOwner().getId()).getEmail();
        clientReview.setOwnerEmail(ownerEmail);

        return reviewRepository.save(clientReview);
    }

    @Override
    public Collection<ClientReview> getAllPendingReviews() {
        return reviewRepository.findAllByStatus(ReviewStatus.PENDING);
    }

    @Override
    public void handleReview(Long id, Boolean approved) {
        try {
            ClientReview review = reviewRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Review not found"));

            if (review.getStatus() != ReviewStatus.PENDING) {
                throw new ResourceConflictException("Review already processed!");
            }

            if (approved) {
                review.setStatus(ReviewStatus.APPROVED);
                emailService.sendReviewApprovedEmail(review);
                updateRatings(review);

            } else {
                review.setStatus(ReviewStatus.REJECTED);
            }
            reviewRepository.save(review);

        } catch (ObjectOptimisticLockingFailureException e) {
            loggerLog.debug("Optimistic lock exception");
        }
    }

    private void updateRatings(ClientReview review){
        double rentalRating = reviewRepository.calculateRentalRating(review.getRentalId());
        double ownerRating = reviewRepository.calculateOwnerRating(review.getOwnerEmail());
        if(review.getReservationType() == ReservationType.VACATION_HOME){
            homeService.updateHomeRating(review.getRentalId(), rentalRating);
        } else if(review.getReservationType() == ReservationType.BOAT){
            boatService.updateBoatRating(review.getRentalId(), rentalRating);
        } else if(review.getReservationType() == ReservationType.ADVENTURE){
            adventureService.updateAdventureRating(review.getRentalId(), rentalRating, ownerRating);
        }
    }
}

