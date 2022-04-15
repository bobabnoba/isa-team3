package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.repository.UserRepository;
import com.ftn.fishingbooker.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private UserRepository repository;

    @Override
    public void registerClient(Client client) {

        //TODO:
        repository.save(client);
        sendRegistrationEmail(client);
    }

    private void sendRegistrationEmail(Client client) {
    }
}

