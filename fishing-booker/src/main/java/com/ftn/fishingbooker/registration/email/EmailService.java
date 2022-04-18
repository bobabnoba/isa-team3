package com.ftn.fishingbooker.registration.email;

import javax.mail.MessagingException;

public interface EmailService {

    String createConfirmRegistrationEmail(String email, String token);

    void sendEmail(String to, String subject, String emailContent) throws MessagingException;
}
