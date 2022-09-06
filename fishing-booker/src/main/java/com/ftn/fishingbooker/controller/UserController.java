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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Collection<UserInfoDto>> getAllUserInfo() {
        Collection<User> found = userService.findAllByDeleted(false);
        Collection<UserInfoDto> dtos = found.stream()
                .map(UserMapper::mapToInfoDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("{email}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String email) {
        User userInfo = userService.getByEmail(email);
        return ResponseEntity.ok(UserMapper.mapToDto(userInfo));
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable Long id) {
        User userInfo = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.mapToDto(userInfo));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = userService.update(UserMapper.mapToEntity(userDto));
        return ResponseEntity.ok(UserMapper.mapToDto(user));
    }

    @GetMapping("/reservations/upcoming/{userEmail}")
    public Collection<ReservationDto> GetClientReservations(@PathVariable String userEmail) {
        List<Reservation> reservationList = clientService.getUpcomingReservations(userEmail);
        return ReservationMapper.map(reservationList);
    }

    @PutMapping(value = "change-password/{email}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public ResponseEntity<HttpStatus> changePassword(@RequestBody PasswordChangeDto request, @PathVariable String email) {
        userService.changePassword(email, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/has-incoming-reservations")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<Boolean> userHasIncomingReservations(@PathVariable Long id, @RequestParam String role) {
        return ResponseEntity.ok(reservationService.getNoOfIncomingReservationsForUser(id, role) > 0);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
