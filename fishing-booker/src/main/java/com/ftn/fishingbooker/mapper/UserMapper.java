package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.model.BoatOwner;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.HomeOwner;
import com.ftn.fishingbooker.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public List<UserDto> mapToDto(List<User> userList) {
        List<UserDto> userDto = new ArrayList<>();
        for (User u : userList) {
            userDto.add(mapToDto(u));
        }
        return userDto;
    }

    public Client mapToClient(UserDto userDto) {
        Client client = new Client();
        client.setId(userDto.getId());
        client.setFirstName(userDto.getFirstName());
        client.setLastName(userDto.getLastName());
        client.setEmail(userDto.getEmail());
        client.setPassword(userDto.getPassword());
        client.setAddress(userDto.getAddress());
        client.setPhone(userDto.getPhone());
        client.setCity(userDto.getCity());
        client.setCountry(userDto.getCountry());
        client.setActivated(userDto.isActivated());
        client.setBlocked(userDto.isBlocked());

        return client;
    }

    public HomeOwner mapToHomeOwner(UserDto userDto) {
        HomeOwner homeOwner = new HomeOwner();
        homeOwner.setId(userDto.getId());
        homeOwner.setFirstName(userDto.getFirstName());
        homeOwner.setLastName(userDto.getLastName());
        homeOwner.setEmail(userDto.getEmail());
        homeOwner.setPassword(userDto.getPassword());
        homeOwner.setAddress(userDto.getAddress());
        homeOwner.setPhone(userDto.getPhone());
        homeOwner.setCity(userDto.getCity());
        homeOwner.setCountry(userDto.getCountry());
        homeOwner.setActivated(userDto.isActivated());
        homeOwner.setBlocked(userDto.isBlocked());

        return homeOwner;
    }

    public BoatOwner mapToBoatOwner(UserDto userDto) {
        BoatOwner boatOwner = new BoatOwner();
        boatOwner.setId(userDto.getId());
        boatOwner.setFirstName(userDto.getFirstName());
        boatOwner.setLastName(userDto.getLastName());
        boatOwner.setEmail(userDto.getEmail());
        boatOwner.setPassword(userDto.getPassword());
        boatOwner.setAddress(userDto.getAddress());
        boatOwner.setPhone(userDto.getPhone());
        boatOwner.setCity(userDto.getCity());
        boatOwner.setCountry(userDto.getCountry());
        boatOwner.setActivated(userDto.isActivated());
        boatOwner.setBlocked(userDto.isBlocked());

        return boatOwner;
    }

    public UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAddress(user.getAddress());
        userDto.setPhone(user.getPhone());
        userDto.setCity(user.getCity());
        userDto.setCountry(user.getCountry());
        userDto.setActivated(user.isActivated());
        userDto.setBlocked(user.isBlocked());

        return userDto;
    }
}
