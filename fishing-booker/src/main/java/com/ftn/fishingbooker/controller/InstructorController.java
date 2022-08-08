package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.InstructorAvailabilityRequestDto;
import com.ftn.fishingbooker.dto.InstructorAvailabilityResponseDto;
import com.ftn.fishingbooker.dto.InstructorDto;
import com.ftn.fishingbooker.dto.OwnerRegisterDto;
import com.ftn.fishingbooker.mapper.InstructorAvailabilityMapper;
import com.ftn.fishingbooker.mapper.InstructorMapper;
import com.ftn.fishingbooker.mapper.RegistrationMapper;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.InstructorAvailability;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService){
        this.instructorService = instructorService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody OwnerRegisterDto registerDto) throws MessagingException {

        Instructor instructor = RegistrationMapper.mapToInstructor(registerDto);
        User user = instructorService.register(instructor, registerDto.motivation);
        return new ResponseEntity<>(UserMapper.mapToDto(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<InstructorDto> getInstructorWithAvailability(String email){
        Instructor found = instructorService.getWithAvailability(email);
        return ResponseEntity.ok(InstructorMapper.toDto(found));
    }

    @PostMapping("/add-availability")
    public ResponseEntity<InstructorAvailabilityResponseDto> addAvailabilityPeriod(@RequestBody InstructorAvailabilityRequestDto availability){
        InstructorAvailability saved = instructorService.addAvailabilityPeriod(InstructorAvailabilityMapper.mapToEntity(availability), availability.getInstructorEmail());
        return ok(InstructorAvailabilityMapper.mapToResponse(saved));
    }

}
