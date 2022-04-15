package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.LoginDto;
import com.ftn.fishingbooker.dto.RegisterDto;
import com.ftn.fishingbooker.dto.UserTokenStateDto;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.security.util.TokenUtils;
import com.ftn.fishingbooker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<UserTokenStateDto> login(
            @RequestBody LoginDto authRequest, HttpServletResponse response) {
        /**
         * Kako se ovo odvija:
         * Authentication manager koristi metodu findUserByUsername da bi iscupao usera iz baze
         * U nasem slucaju ta metoda jer overrided da izvlaci po email-u
         * U slucaju da kredencijalni nisu ispravni desice se Authentication Exception
         */
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(), authRequest.getPassword()));

        /**
         * Kada je auth uspjesna ubaci korisnika u trenutni security context
         * i kreiraj token za tog korisnika
         */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getEmail());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenStateDto(jwt, expiresIn));
    }

    @PostMapping("/register/client")
    public ResponseEntity<User> registerClient(@RequestBody RegisterDto registerDto, UriComponentsBuilder builder)
            throws MessagingException {

        User user = userService.createClient(registerDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/register/homeOwner")
    public ResponseEntity<User> registerHomeOwner(@RequestBody RegisterDto registerDto, UriComponentsBuilder builder)
            throws MessagingException {

        User user = userService.createHomeOwner(registerDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/register/fishingInstructor")
    public ResponseEntity<User> registerFishingInstructor(@RequestBody RegisterDto registerDto, UriComponentsBuilder builder)
            throws MessagingException {

        User user = userService.createFishingInstructor(registerDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody RegisterDto registerDto, UriComponentsBuilder builder)
            throws MessagingException {

        User user = userService.createAdmin(registerDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
