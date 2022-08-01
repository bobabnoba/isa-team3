package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.ClientType;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    private static PasswordEncoder passwordEncoder;
    private static RoleService roleService;

    @Autowired
    public RegistrationMapper(PasswordEncoder passwordEncoder, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public static Client mapToClient(RegisterDto registerDto) {
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

    public static User mapToOwner(OwnerRegisterDto registerDto) {
        User newOwner = new User();
        newOwner.setFirstName(registerDto.getFirstName());
        newOwner.setLastName(registerDto.getLastName());
        newOwner.setEmail(registerDto.getEmail());
        newOwner.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        newOwner.setAddress(registerDto.getAddress());
        newOwner.setPhone(registerDto.getPhone());
        newOwner.setCity(registerDto.getCity());
        newOwner.setCountry(registerDto.getCountry());
        newOwner.setActivated(false);
        newOwner.setBlocked(false);
        if (registerDto.registrationType.name().equals("VACATION_HOUSE_ADVERTISER")){
            newOwner.setRole(roleService.findByName("ROLE_HOME_OWNER"));
        } else if (registerDto.registrationType.name().equals("VACATION_BOAT_ADVERTISER")){
            newOwner.setRole(roleService.findByName("ROLE_BOAT_OWNER"));
        } else if (registerDto.registrationType.name().equals("INSTRUCTOR_ADVERTISER")) {
            newOwner.setRole(roleService.findByName("ROLE_INSTRUCTOR"));
        }
        return newOwner;
    }

    public static Admin mapToAdmin(RegisterDto registerDto) {
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

    public static RegistrationResponseDto mapToResponse(Registration registration){
        RegistrationResponseDto dto = new RegistrationResponseDto();
        dto.setId(registration.getId());
        dto.setUserEmail(registration.getUserEmail());
        dto.setType(registration.getType());
        dto.setMotivation(registration.getMotivation());
        return dto;
    }

    public static Registration mapToRegistration(RegistrationResponseDto dto){
        Registration registration = new Registration();
        registration.setId(dto.getId());
        registration.setType(dto.getType());
        registration.setMotivation(dto.getMotivation());
        registration.setApproved(dto.isApproved());
        registration.setUserEmail(dto.getUserEmail());
        registration.setAdminResponse(dto.getResponse());
        return registration;
    }

}
