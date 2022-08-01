package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.ConfirmationToken;
import com.ftn.fishingbooker.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository repository;

    public ConfirmationToken save(ConfirmationToken token) {
        return repository.save(token);
    }

    public ConfirmationToken findByConfirmationToken(String token) {
        return repository.findByToken(token);
    }

    public void delete(ConfirmationToken confirmationToken) {
        repository.delete(confirmationToken);
    }
}
