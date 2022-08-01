package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("{email}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String email){
        User userInfo = userService.getByEmail(email);
        return ResponseEntity.ok(UserMapper.mapToDto(userInfo));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        User user = userService.update(UserMapper.mapToEntity(userDto));
        return ResponseEntity.ok(UserMapper.mapToDto(user));
    }
}
