package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.RegisterDto;
import com.ftn.fishingbooker.enumeration.ClientType;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    public Client mapToClient(RegisterDto registerDto) {
        Client client = new Client();
        client.setId(null);
        client.setFirstName(registerDto.getFirstName());
        client.setLastName(registerDto.getLastName());
        client.setEmail(registerDto.getEmail());
        client.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        client.setAddress(registerDto.getAddress());
        client.setPhone(registerDto.getPhone());
        client.setCity(registerDto.getCity());
        client.setCountry(registerDto.getCountry());
        client.setActivated(false);
        client.setBlocked(false);
        client.setPoints(0);
        client.setType(ClientType.SILVER);
        UserRole role = roleService.findByName("ROLE_CLIENT");
        client.setRole(role);

        return client;
    }

    public HomeOwner mapToHomeOwner(RegisterDto registerDto) {
        HomeOwner homeOwner = new HomeOwner();

        homeOwner.setId(null);
        homeOwner.setFirstName(registerDto.getFirstName());
        homeOwner.setLastName(registerDto.getLastName());
        homeOwner.setEmail(registerDto.getEmail());
        homeOwner.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        homeOwner.setAddress(registerDto.getAddress());
        homeOwner.setPhone(registerDto.getPhone());
        homeOwner.setCity(registerDto.getCity());
        homeOwner.setCountry(registerDto.getCountry());
        homeOwner.setActivated(false);
        homeOwner.setBlocked(false);
        homeOwner.setRole(roleService.findByName("ROLE_HOME_OWNER"));


        return homeOwner;
    }

    public BoatOwner mapToBoatOwner(RegisterDto registerDto) {
        BoatOwner boatOwner = new BoatOwner();

        boatOwner.setId(null);
        boatOwner.setFirstName(registerDto.getFirstName());
        boatOwner.setLastName(registerDto.getLastName());
        boatOwner.setEmail(registerDto.getEmail());
        boatOwner.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        boatOwner.setAddress(registerDto.getAddress());
        boatOwner.setPhone(registerDto.getPhone());
        boatOwner.setCity(registerDto.getCity());
        boatOwner.setCountry(registerDto.getCountry());
        boatOwner.setActivated(false);
        boatOwner.setBlocked(false);

        boatOwner.setRole(roleService.findByName("ROLE_BOAT_OWNER"));

        return boatOwner;
    }

    public Admin mapToAdmin(RegisterDto registerDto) {
        Admin admin = new Admin();

        admin.setId(null);
        admin.setFirstName(registerDto.getFirstName());
        admin.setLastName(registerDto.getLastName());
        admin.setEmail(registerDto.getEmail());
        admin.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        admin.setAddress(registerDto.getAddress());
        admin.setPhone(registerDto.getPhone());
        admin.setCity(registerDto.getCity());
        admin.setCountry(registerDto.getCountry());
        admin.setActivated(false);
        admin.setBlocked(false);
        admin.setRole(roleService.findByName("ROLE_ADMIN"));

        return admin;
    }

    public Instructor mapToInstructor(RegisterDto registerDto) {
        Instructor instructor = new Instructor();

        instructor.setId(null);
        instructor.setFirstName(registerDto.getFirstName());
        instructor.setLastName(registerDto.getLastName());
        instructor.setEmail(registerDto.getEmail());
        instructor.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        instructor.setAddress(registerDto.getAddress());
        instructor.setPhone(registerDto.getPhone());
        instructor.setCity(registerDto.getCity());
        instructor.setCountry(registerDto.getCountry());
        instructor.setActivated(false);
        instructor.setBlocked(false);
        instructor.setRole(roleService.findByName("ROLE_INSTRUCTOR"));

        return instructor;
    }
}
