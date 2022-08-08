package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.InstructorDto;
import com.ftn.fishingbooker.dto.OwnerRegisterDto;
import com.ftn.fishingbooker.mapper.InstructorMapper;
import com.ftn.fishingbooker.mapper.RegistrationMapper;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Collection;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody OwnerRegisterDto registerDto) throws MessagingException {

        Instructor instructor = RegistrationMapper.mapToInstructor(registerDto);
        User user = instructorService.register(instructor, registerDto.motivation);
        return new ResponseEntity<>(UserMapper.mapToDto(user), HttpStatus.CREATED);
    }

    @GetMapping()
    public Collection<InstructorDto> GetAll() {
        Collection<Instructor> instructors = instructorService.getAll();

        return InstructorMapper.mapInstructors(instructors);
    }
}
