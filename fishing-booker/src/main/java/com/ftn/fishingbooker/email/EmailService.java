package com.ftn.fishingbooker.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {

    String createConfirmRegistrationEmail(String token);

    void sendEmail(String email, String confirm_registration, String html) throws AddressException, MessagingException;
}
