package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDto {

    public Long id;

    public String firstName;

    public UserDto(User u) {

        this.id = u.getId();
        this.firstName = u.getFirstName();
    }
}
