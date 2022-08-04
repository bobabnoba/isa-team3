package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.DeleteAccountRequest;

import javax.mail.MessagingException;
import java.util.Collection;

public interface DeleteAccountService {

    DeleteAccountRequest createRequest(DeleteAccountRequest request);
    Collection<DeleteAccountRequest> getAllUnprocessed();
    void processRequest(DeleteAccountRequest request) throws MessagingException;
}
