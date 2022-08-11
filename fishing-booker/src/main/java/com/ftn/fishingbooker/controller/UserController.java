package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.service.ReservationService;
import com.ftn.fishingbooker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ReservationService reservationService;

    @GetMapping("{email}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String email) {
        User userInfo = userService.getByEmail(email);
        return ResponseEntity.ok(UserMapper.mapToDto(userInfo));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = userService.update(UserMapper.mapToEntity(userDto));
        return ResponseEntity.ok(UserMapper.mapToDto(user));
    }

    @GetMapping("/reservations/{userId}")
    public Collection<ReservationDto> GetClientReservations(@PathVariable Long userId) {

        return ReservationMapper.map(reservationService.getReservationsForClient(userId));
    }
}
