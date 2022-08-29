package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.ClientReviewDto;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.ClientReview;

public interface ClientReviewService {

    ClientReview save(ClientReview clientReview);

    ClientReview newClientReview(Client client, ClientReviewDto clientReviewDto);
}
