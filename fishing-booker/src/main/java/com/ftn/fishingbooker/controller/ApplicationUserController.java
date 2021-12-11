package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService userService;
    @Autowired
    private UserMapper userMapper;


    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userMapper.mapToDto(userService.getAll()), HttpStatus.OK);
    }


}
