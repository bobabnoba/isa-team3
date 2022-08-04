package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.DeleteAccountRequest;
import com.ftn.fishingbooker.repository.DeleteAccountRepository;
import com.ftn.fishingbooker.service.DeleteAccountService;
import com.ftn.fishingbooker.service.EmailService;
import com.ftn.fishingbooker.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Collection;

@Service
public class DeleteAccountServiceImpl implements DeleteAccountService {

    private final DeleteAccountRepository deleteAccountRepository;
    private final UserService userService;
    private final EmailService emailService;

    public DeleteAccountServiceImpl(DeleteAccountRepository deleteAccountRepository, UserService userService, EmailService emailService) {
        this.deleteAccountRepository = deleteAccountRepository;
        this.userService = userService;
        this.emailService = emailService;
    }

    @Transactional
    @Override
    public DeleteAccountRequest createRequest(DeleteAccountRequest request) {
        return deleteAccountRepository.save(request);
    }

    @Override
    public Collection<DeleteAccountRequest> getAllUnprocessed() {
        return deleteAccountRepository.getAllUnprocessed();
    }

    @Transactional
    @Override
    public void processRequest(DeleteAccountRequest request) throws MessagingException {
        if (request.isApproved()){
            userService.delete(request.getEmail());
        }
        String html = emailService.createDeleteAccountResponseEmail(request.getAdminResponse(), request.isApproved());
        emailService.sendEmail(request.getEmail(), "Delete account response", html);

        deleteAccountRepository.save(request);
    }
}
