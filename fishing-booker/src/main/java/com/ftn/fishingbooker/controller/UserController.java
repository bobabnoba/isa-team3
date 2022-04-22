package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.security.util.*;
import com.ftn.fishingbooker.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeleteAccountRequestRepository deleteAccountRequestRepository;
    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/deleteAccount")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> deleteAccount(@RequestBody DeleteAccountRequestDto deleteAccountRequestDto,  @RequestHeader (name="Authorization") String token) {
        var a = token.substring(7);
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            deleteAccountRequestDto.userEmail = userDetails.getUsername();
            DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(deleteAccountRequestDto.userEmail, deleteAccountRequestDto.request);
            deleteAccountRequestRepository.save(deleteAccountRequest);
            return new ResponseEntity<DeleteAccountRequest>(deleteAccountRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            e.getMessage();
        }
        return new ResponseEntity<DeleteAccountRequest>(HttpStatus.BAD_REQUEST);
    }

}
