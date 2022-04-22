package com.ftn.fishingbooker.registration.email;

import javax.mail.MessagingException;

public interface EmailService {

    String createConfirmRegistrationEmail(String email, String token);

    String createAdminResponseEmail( String message, boolean isApproved);

    void sendEmail(String to, String subject, String emailContent) throws MessagingException;

    String createAdminDeleteAccountResponse(String response, boolean b);
}
