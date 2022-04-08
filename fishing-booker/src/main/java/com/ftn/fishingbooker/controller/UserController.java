package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
