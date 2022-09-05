package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.exception.EntityNotFoundException;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.DeleteAccountRequest;
import com.ftn.fishingbooker.repository.DeleteAccountRepository;
import com.ftn.fishingbooker.service.DeleteAccountService;
import com.ftn.fishingbooker.service.EmailService;
import com.ftn.fishingbooker.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class DeleteAccountServiceImpl implements DeleteAccountService {

    private final DeleteAccountRepository deleteAccountRepository;
    private final UserService userService;
    private final EmailService emailService;

    protected final Log loggerLog = LogFactory.getLog(getClass());

    public DeleteAccountServiceImpl(DeleteAccountRepository deleteAccountRepository, UserService userService, EmailService emailService) {
        this.deleteAccountRepository = deleteAccountRepository;
        this.userService = userService;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public DeleteAccountRequest createRequest(DeleteAccountRequest request) {
        return deleteAccountRepository.save(request);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<DeleteAccountRequest> getAllUnprocessed() {
        return deleteAccountRepository.getAllUnprocessed();
    }

    @Transactional
    @Override
    public void processRequest(Long id, DeleteAccountRequest request) {

        try {
            DeleteAccountRequest found = deleteAccountRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Delete Account Request not found")
            );

            if (found.getAdminResponse() != null) {
               throw new ResourceConflictException("Delete Account Request already processed!");
            }

            found.setApproved(request.isApproved());
            found.setAdminResponse(request.getAdminResponse());

            if (found.isApproved()) {
                userService.delete(found.getEmail());
            }
            deleteAccountRepository.save(found);
            emailService.sendDeleteAccountResponseEmail(found);

        } catch (ObjectOptimisticLockingFailureException e) {
            loggerLog.debug("Optimistic lock exception");
        }
    }
}
