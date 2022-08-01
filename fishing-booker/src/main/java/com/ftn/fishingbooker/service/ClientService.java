package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.User;

import javax.mail.MessagingException;

public interface ClientService {

    void registerClient(Client client) throws MessagingException;
}
