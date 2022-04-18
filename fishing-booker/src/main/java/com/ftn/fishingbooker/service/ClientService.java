package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.Client;

import javax.mail.MessagingException;

public interface ClientService {

    void registerClient(Client client) throws MessagingException;
}
