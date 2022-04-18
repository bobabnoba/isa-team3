package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.LoginDto;
import com.ftn.fishingbooker.dto.RegisterDto;
import com.ftn.fishingbooker.dto.UserTokenStateDto;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.registration.RegistrationService;
import com.ftn.fishingbooker.security.util.TokenUtils;
import com.ftn.fishingbooker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RegistrationService registrationService;

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
        } catch (Exception e) {
            throw new ResourceConflictException(" Sth wrong not credentials !");
        }

    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @PostMapping("/register/client")
    public ResponseEntity<Object> registerClient(@RequestBody RegisterDto registerDto) throws MessagingException {
        User user = userService.createClient(registerDto);
        return new ResponseEntity<>(userMapper.mapToDto(user), HttpStatus.CREATED);
    }

    @PostMapping("/register/homeOwner")
    public ResponseEntity<Object> registerHomeOwner(@RequestBody RegisterDto registerDto, UriComponentsBuilder builder)
            throws MessagingException {
        try {
            User user = userService.createHomeOwner(registerDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

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
