package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.PasswordChangeDto;
import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.dto.UserInfoDto;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.ReservationService;
import com.ftn.fishingbooker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ClientService clientService;
    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers() {
        Collection<User> found = userService.getAll();
        return ResponseEntity.ok(found);
    }

    @GetMapping("/info")
    public ResponseEntity<Collection<UserInfoDto>> getAllUserInfo() {
        Collection<User> found = userService.findAllByDeleted(false);
        Collection<UserInfoDto> dtos = found.stream()
                .map(UserMapper::mapToInfoDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("{email}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String email) {
        User userInfo = userService.getByEmail(email);
        return ResponseEntity.ok(UserMapper.mapToDto(userInfo));
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable Long id) {
        User userInfo = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.mapToDto(userInfo));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = userService.update(UserMapper.mapToEntity(userDto));
        return ResponseEntity.ok(UserMapper.mapToDto(user));
    }

    @GetMapping("/reservations/upcoming/{userEmail}")
    public Collection<ReservationDto> GetClientReservations(@PathVariable String userEmail) {
        List<Reservation> reservationList = clientService.getUpcomingReservations(userEmail);
        return ReservationMapper.map(reservationList);
    }

    @PostMapping("/cancel/reservation/{userEmail}")
    public ResponseEntity<Collection<ReservationDto>> CancelUpcomingReservation(@PathVariable String userEmail, @RequestBody Long reservationId) {
       
        boolean isCanceled = clientService.cancelUpcomingReservation(reservationId, userEmail);
        List<Reservation> reservationList = clientService.getUpcomingReservations(userEmail);
        if (isCanceled == true) {
            return ResponseEntity.ok(ReservationMapper.map(reservationList));
        }
        return new ResponseEntity<>(ReservationMapper.map(reservationList), HttpStatus.CONFLICT);
    }

    @PutMapping(value = "change-password/{email}")
    public ResponseEntity<HttpStatus> changePassword(@RequestBody PasswordChangeDto request, @PathVariable String email)
    {
        userService.changePassword(email, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/has-incoming-reservations")
    public ResponseEntity<Boolean> userHasIncomingReservations(@PathVariable Long id, @RequestParam String role){
        return ResponseEntity.ok(reservationService.getNoOfIncomingReservationsForUser(id, role) > 0);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
