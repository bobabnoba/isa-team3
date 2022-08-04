package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.DeleteAccountRequestDto;
import com.ftn.fishingbooker.dto.DeleteAccountResponse;
import com.ftn.fishingbooker.mapper.DeleteAccountRequestMapper;
import com.ftn.fishingbooker.model.DeleteAccountRequest;
import com.ftn.fishingbooker.service.DeleteAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Collection;

@RestController
@RequestMapping("/delete-account")
public class DeleteAccountController {

    private final DeleteAccountService deleteAccountService;

    public DeleteAccountController(DeleteAccountService deleteAccountService) {
        this.deleteAccountService = deleteAccountService;
    }

    @PostMapping
    public ResponseEntity<DeleteAccountResponse> createDeletionRequest(@RequestBody DeleteAccountRequestDto request) {
        DeleteAccountRequest savedRequest = deleteAccountService.createRequest(DeleteAccountRequestMapper.mapToEntity(request));
        return ResponseEntity.ok(DeleteAccountRequestMapper.mapToDto(savedRequest));
    }

    @GetMapping
    public ResponseEntity<Collection<DeleteAccountResponse>> getAllDeletionRequests(){
        Collection<DeleteAccountRequest> requests = deleteAccountService.getAllUnprocessed();
        Collection<DeleteAccountResponse> dtos = requests.stream()
                .map(DeleteAccountRequestMapper::mapToDto)
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/process-request")
    public ResponseEntity<Void> processDeletionRequest(@RequestBody DeleteAccountResponse request) {
        try {
            deleteAccountService.processRequest(DeleteAccountRequestMapper.mapToEntityFromResponse(request));
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String email) {
       // userService.delete(email);
        return ResponseEntity.noContent().build();
    }

}
