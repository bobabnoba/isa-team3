package com.ftn.fishingbooker.service;

import javax.mail.MessagingException;

public interface RegistrationService {

    public String generateRegistrationToken(String email, String name);

    public void sendRegistrationEmail(String client, String email) throws MessagingException;

    String confirmToken(String token);


}
