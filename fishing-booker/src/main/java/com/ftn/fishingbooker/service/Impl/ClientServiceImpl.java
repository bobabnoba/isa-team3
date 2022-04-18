package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.registration.RegistrationService;
import com.ftn.fishingbooker.repository.UserRepository;
import com.ftn.fishingbooker.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private RegistrationService registrationService;

    @Override
    public void registerClient(Client client) throws MessagingException {
        repository.save(client);
        String registrationToken = registrationService.generateRegistrationToken(client.getEmail(), client.getRole().getName());
        registrationService.sendRegistrationEmail(registrationToken, client.getEmail());
    }

}

