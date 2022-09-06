package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.ClientReviewDto;
import com.ftn.fishingbooker.dto.ReviewDto;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.enumeration.ReviewStatus;
import com.ftn.fishingbooker.mapper.ReviewMapper;
import com.ftn.fishingbooker.model.ClientReview;
import com.ftn.fishingbooker.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reservation/{reservationId}")
    public Boolean checkForReview(@PathVariable Long reservationId){

        return reviewService.checkForReview(reservationId);
    }

    @PostMapping("/{clientEmail}")
    public ResponseEntity CreateReview(@PathVariable String clientEmail, @RequestBody ClientReviewDto clientReviewDto) {

        ClientReview clientReview = ReviewMapper.map(clientReviewDto);
        clientReview.setClientEmail(clientEmail);
        clientReview.setStatus(ReviewStatus.PENDING);

        if (clientReviewDto.getReservationType() == ReservationType.ADVENTURE) {

            return ResponseEntity.ok(reviewService.makeAdventureReview(clientReview));

        } else if (clientReviewDto.getReservationType() == ReservationType.BOAT) {
            return ResponseEntity.ok(reviewService.makeBoatReview(clientReview));


        } else if (clientReviewDto.getReservationType() == ReservationType.VACATION_HOME) {
            return ResponseEntity.ok(reviewService.makeVacationHomeReview(clientReview));

        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/pending")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Collection<ReviewDto>> getAllPending(){
        Collection<ClientReview> found = reviewService.getAllPendingReviews();
        Collection<ReviewDto> dtos = found.stream()
                .map(ReviewMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/handle-review/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> handleReview(@PathVariable Long id, @RequestBody Boolean approved) {
        reviewService.handleReview(id, approved);
        return ResponseEntity.ok().build();
    }

}
