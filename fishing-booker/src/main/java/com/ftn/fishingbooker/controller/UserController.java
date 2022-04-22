package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
    public ResponseEntity<?> deleteAccount(@RequestBody DeleteAccountRequestDto deleteAccountRequestDto){
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(deleteAccountRequestDto.userEmail, deleteAccountRequestDto.request);
        deleteAccountRequestRepository.save(deleteAccountRequest);
        return new ResponseEntity<DeleteAccountRequest>(deleteAccountRequest, HttpStatus.CREATED);
    }

}
