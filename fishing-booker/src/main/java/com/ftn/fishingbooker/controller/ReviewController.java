package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.ClientReviewDto;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.enumeration.ReviewStatus;
import com.ftn.fishingbooker.mapper.ReviewMapper;
import com.ftn.fishingbooker.model.ClientReview;
import com.ftn.fishingbooker.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
