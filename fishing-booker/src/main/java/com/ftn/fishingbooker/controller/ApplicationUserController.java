package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.service.ApplicationUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto save(@RequestBody UserDto newUser){
        Long id = userService.save(newUser);
        return userService.get(id);
    }

    @PutMapping("{id}")
    public UserDto save(@PathVariable Long id, @RequestBody UserDto userDto){
        userService.save(userDto);
        return  userService.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }


}
