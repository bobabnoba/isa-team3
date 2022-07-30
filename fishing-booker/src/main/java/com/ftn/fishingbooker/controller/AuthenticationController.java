package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.service.RegistrationService;
import com.ftn.fishingbooker.security.util.TokenUtils;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RegistrationService registrationService;

    public AuthenticationController(TokenUtils tokenUtils, AuthenticationManager authenticationManager, UserService userService, RegistrationService registrationService){
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenStateDto> login(
            @RequestBody LoginDto authRequest, HttpServletResponse response) {
        /**
         * Kako se ovo odvija:
         * Authentication manager koristi metodu findUserByUsername da bi iscupao usera iz baze
         * U nasem slucaju ta metoda jer overrided da izvlaci po email-u
         * U slucaju da kredencijalni nisu ispravni desice se Authentication Exception
         */
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(), authRequest.getPassword()));

            /**
             * Kada je auth uspjesna ubaci korisnika u trenutni security context
             * i kreiraj token za tog korisnika
             */
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();
            String jwt = tokenUtils.generateToken(user.getEmail(), user.getRole().getName(), false);
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new UserTokenStateDto(jwt, expiresIn));
        } catch (BadCredentialsException e) {
            throw new ResourceConflictException("Bad Credentials!");
        }

    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @PostMapping("/register/client")
    public ResponseEntity<Object> registerClient(@RequestBody RegisterDto registerDto) throws MessagingException {
        User user = userService.createClient(registerDto);
        return new ResponseEntity<>(UserMapper.mapToDto(user), HttpStatus.CREATED);
    }

    @PostMapping("/register/owner")
    public ResponseEntity<Object> registerOwner(@RequestBody OwnerRegisterDto registerDto) throws MessagingException {

        User owner = RegistrationMapper.mapToOwner(registerDto);
        User user = userService.registerOwner(owner, registerDto.registrationType, registerDto.motivation);
        return new ResponseEntity<>(UserMapper.mapToDto(user), HttpStatus.CREATED);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody RegisterDto registerDto) {
        User user = userService.createAdmin(registerDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
