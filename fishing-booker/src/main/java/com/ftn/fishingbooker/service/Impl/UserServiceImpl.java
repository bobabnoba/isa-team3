package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.PasswordChangeDto;
import com.ftn.fishingbooker.dto.RegisterDto;
import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.exception.InvalidPasswordException;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.mapper.RegistrationMapper;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.RegistrationRepository;
import com.ftn.fishingbooker.repository.UserRepository;
import com.ftn.fishingbooker.service.AdminService;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ClientService clientService;
    private final RegistrationRepository registrationRepository;
    private final PasswordEncoder encoder;
    private final AdminService adminService;

    private static String url = "<a href=\"http://localhost:4200/login\"> Login </a>";


    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User was not found"));
        } else {
            return user;
        }
    }

    @Override
    public User createClient(RegisterDto registerDto) throws ResourceConflictException, MessagingException {
        if (isEmailRegistered(registerDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        Client user = RegistrationMapper.mapToClient(registerDto);
        clientService.registerClient(user);
        return user;
    }

    @Override
    public User createAdmin(RegisterDto registerDto) {
        if (isEmailRegistered(registerDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        Admin user = RegistrationMapper.mapToAdmin(registerDto);
        //TODO:
        return user;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User found = userRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));
        found.setDeleted(true);
        userRepository.save(found);
    }

    @Override
    public String enableUser(String email) {
        User u = userRepository.findByEmail(email);
        u.setActivated(true);
        return url;
    }

    @Override
    public User registerOwner(User owner, RegistrationType type, String motivation) throws MessagingException {

        if (isEmailRegistered(owner.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }

        Registration registration = new Registration(type, motivation, owner.getEmail());
        registrationRepository.save(registration);


        return userRepository.save(owner);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User update(User user) {
        User dbUser = userRepository.findByEmail(user.getEmail());
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setPhone(user.getPhone());
        dbUser.setAddress(user.getAddress());
        dbUser.setBiography(user.getBiography());
        return userRepository.save(dbUser);
    }

    @Transactional
    @Override
    public void delete(String email) {
        User dbUser = userRepository.findByEmail(email);
        dbUser.setDeleted(true);
        userRepository.save(dbUser);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Collection<User> findAllByDeleted(boolean deleted) {
        return userRepository.findAllByDeleted(deleted);
    }

    @Override
    public Collection<User> saveAll(Collection<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceConflictException("User not found"));
    }

    @Override
    public void changePassword(String email, PasswordChangeDto request)  {
        User user = userRepository.findByEmail(email);

        if(encoder.matches(request.getOldPassword(), user.getPassword())){
            user.setPassword(encoder.encode(request.getNewPassword()));
            if(user.getRole().getName().equals("ROLE_ADMIN")){
                Admin admin = (Admin) user;
                adminService.updateFirstLogin(admin);
            }
            userRepository.save(user);
        } else {
            throw new InvalidPasswordException("Invalid password!");
        }
    }
    @Override
    public void resetAllPenalties() {
        userRepository.resetAllPenalties();
    }
}
