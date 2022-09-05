package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.service.EmailService;
import com.ftn.fishingbooker.service.RegistrationService;
import com.ftn.fishingbooker.service.UserService;
import com.ftn.fishingbooker.model.ConfirmationToken;
import com.ftn.fishingbooker.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    private UserService userService;

    /**
     * Creates confirmation token and saves it to repository
     *
     * @param email connects token to a user
     * @return jwt
     */
    @Override
    public String generateRegistrationToken(String email, String role) {
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setEmail(email);
        confirmationToken.setToken(tokenUtils.generateToken(email, role, false));
        confirmationTokenService.save(confirmationToken);
        return confirmationToken.getToken();
    }

    @Override
    public void sendRegistrationEmail(String token, String email) throws MessagingException {
        String html = emailService.createConfirmRegistrationEmail(email, token);
        emailService.sendEmail(email, "Confirm your e-mail", html);
    }

    @Override
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.findByConfirmationToken(token);
        return userService.enableUser(confirmationToken.getEmail());
    }
}
