package com.ftn.fishingbooker.mapper;


import com.ftn.fishingbooker.dto.OwnerRegisterDto;
import com.ftn.fishingbooker.dto.RegisterDto;
import com.ftn.fishingbooker.dto.RegistrationResponseDto;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.RoleService;
import com.ftn.fishingbooker.service.UserRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    private static PasswordEncoder passwordEncoder;
    private static RoleService roleService;
    private static UserRankService rankService;

    @Autowired
    public RegistrationMapper(PasswordEncoder passwordEncoder, RoleService roleService, UserRankService rankService) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.rankService = rankService;
    }

    public static Client mapToClient(RegisterDto registerDto) {
        Client client = new Client();
        client.setFirstName(registerDto.getFirstName());
        client.setLastName(registerDto.getLastName());
        client.setEmail(registerDto.getEmail());
        client.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        client.setAddress(AddressMapper.toEntity(registerDto.getAddress()));
        client.setPhone(registerDto.getPhone());
        client.setActivated(false);
        client.setBlocked(false);
        client.setPoints(0);
        UserRole role = roleService.findByName("ROLE_CLIENT");
        client.setRole(role);

        return client;
    }

    public static HomeOwner mapToHomeOwner(OwnerRegisterDto registerDto) {
        HomeOwner homeOwner = new HomeOwner();

        homeOwner.setFirstName(registerDto.getFirstName());
        homeOwner.setLastName(registerDto.getLastName());
        homeOwner.setEmail(registerDto.getEmail());
        homeOwner.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        homeOwner.setAddress(AddressMapper.toEntity(registerDto.getAddress()));
        homeOwner.setPhone(registerDto.getPhone());
        homeOwner.setActivated(false);
        homeOwner.setBlocked(false);

        UserRole role = roleService.findByName("ROLE_HOME_OWNER");
        homeOwner.setRole(role);
        homeOwner.setRank(rankService.findByName("REGULAR_ADVERTISER"));

        return homeOwner;
    }

    public static BoatOwner mapToBoatOwner(OwnerRegisterDto registerDto) {
        BoatOwner boatOwner = new BoatOwner();

        boatOwner.setFirstName(registerDto.getFirstName());
        boatOwner.setLastName(registerDto.getLastName());
        boatOwner.setEmail(registerDto.getEmail());
        boatOwner.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        boatOwner.setAddress(AddressMapper.toEntity(registerDto.getAddress()));
        boatOwner.setPhone(registerDto.getPhone());
        boatOwner.setActivated(false);
        boatOwner.setBlocked(false);

        boatOwner.setRole(roleService.findByName("ROLE_BOAT_OWNER"));
        boatOwner.setRank(rankService.findByName("REGULAR_ADVERTISER"));

        return boatOwner;
    }

    public static Admin mapToAdmin(RegisterDto registerDto) {
        Admin admin = new Admin();

        admin.setFirstName(registerDto.getFirstName());
        admin.setLastName(registerDto.getLastName());
        admin.setEmail(registerDto.getEmail());
        admin.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        admin.setAddress(AddressMapper.toEntity(registerDto.getAddress()));
        admin.setActivated(false);
        admin.setBlocked(false);
        admin.setRole(roleService.findByName("ROLE_ADMIN"));

        return admin;
    }

    public static Instructor mapToInstructor(OwnerRegisterDto registerDto) {
        Instructor instructor = new Instructor();

        instructor.setFirstName(registerDto.getFirstName());
        instructor.setLastName(registerDto.getLastName());
        instructor.setEmail(registerDto.getEmail());
        instructor.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        instructor.setAddress(AddressMapper.toEntity(registerDto.getAddress()));
        instructor.setPhone(registerDto.getPhone());
        instructor.setActivated(false);
        instructor.setBlocked(false);
        instructor.setRole(roleService.findByName("ROLE_INSTRUCTOR"));
        instructor.setRank(rankService.findByName("REGULAR_ADVERTISER"));
        return instructor;
    }

    public static RegistrationResponseDto mapToResponse(Registration registration) {
        RegistrationResponseDto dto = new RegistrationResponseDto();
        dto.setId(registration.getId());
        dto.setUserEmail(registration.getUserEmail());
        dto.setType(registration.getType());
        dto.setMotivation(registration.getMotivation());
        return dto;
    }

    public static Registration mapToRegistration(RegistrationResponseDto dto) {
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
