package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.ClientReviewDto;
import com.ftn.fishingbooker.enumeration.ReviewStatus;
import com.ftn.fishingbooker.mapper.ReviewMapper;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.ClientReview;
import com.ftn.fishingbooker.repository.ClientReviewRepository;
import com.ftn.fishingbooker.service.ClientReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientReviewServiceImpl implements ClientReviewService {
    private final ClientReviewRepository clientReviewRepository;

    public ClientReview save(ClientReview clientReview) {
        return clientReviewRepository.save(clientReview);

    }

    @Override
    public ClientReview newClientReview(Client client, ClientReviewDto clientReviewDto) {
        ClientReview clientReview = ReviewMapper.map(clientReviewDto);
        clientReview.setStatus(ReviewStatus.PENDING);
        clientReview.setClient(client);
        return save(clientReview);
    }
}
