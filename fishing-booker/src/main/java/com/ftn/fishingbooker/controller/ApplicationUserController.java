package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.service.ApplicationUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveClient(@RequestBody UserDto newUser) {
        Long id = userService.saveClient(newUser);
        return userService.get(id);
    }

    @PutMapping("{id}")
    public UserDto saveClient(@PathVariable Long id, @RequestBody UserDto userDto) {
        userService.saveClient(userDto);
        return userService.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public UserDto getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }
}
