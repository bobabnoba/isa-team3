package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.Client;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Date;

public interface ClientService {

    void registerClient(Client client) throws MessagingException;

    boolean hasOverlappingReservation(Long clientId, Date startDate, Date endDate);
}
