package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.ReviewStatus;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.model.Boat;
import com.ftn.fishingbooker.model.ClientReview;
import com.ftn.fishingbooker.model.VacationHome;
import com.ftn.fishingbooker.repository.ReviewRepository;
import com.ftn.fishingbooker.service.AdventureService;
import com.ftn.fishingbooker.service.BoatService;
import com.ftn.fishingbooker.service.HomeService;
import com.ftn.fishingbooker.service.ReviewService;
import lombok.RequiredArgsConstructor;
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
    public Boolean checkForReview(Long reservationId) {
        ClientReview review = reviewRepository.findByReservationId(reservationId);
        if (review != null) {
            return true;
        }
        return false;
    }

    @Override
    public Collection<ClientReview> getAllPendingReviews() {
        return reviewRepository.findAllByStatus(ReviewStatus.PENDING);
    }

    @Override
    public void handleReview(Long id, Boolean approved) {
        ClientReview review = reviewRepository.findById(id).orElseThrow(
                () -> new ResourceConflictException("Review not found")
        );
        if(approved){
            review.setStatus(ReviewStatus.APPROVED);
        }else {
            review.setStatus(ReviewStatus.DISAPPROVED);
        }
        reviewRepository.save(review);
    }
}

