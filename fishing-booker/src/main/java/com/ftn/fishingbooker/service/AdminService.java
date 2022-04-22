package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.*;

import javax.mail.*;

public interface AdminService {
    void respondToRegistrationRequest(RegistrationResponseDto registrationResponseDto) throws MessagingException;

    void deleteAccount(DeleteAccountResponseDto deleteAccountResponseDto) throws MessagingException;
}
