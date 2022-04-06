package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.ClientType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.service.AuthenticationService;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.RoleService;
import com.ftn.fishingbooker.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static UserService userService;

    private static PasswordEncoder passwordEncoder;

    private static ClientService clientService;

    private static RoleService roleService;

    private static final String EMAIL_EXISTS = "Email already exists";

    public void saveClient(Client client) throws Exception {
        if (userService.isEmailRegistered()) {
            throw new ResourceConflictException(EMAIL_EXISTS);
        } else {
            client.setPassword(passwordEncoder.encode(client.getPassword()));
            client.setActivated(false);
            clientService.saveClient();
            client.setRole(roleService.findByName("ROLE_CLIENT"));
            client.setPoints(0);
            client.setType(ClientType.SILVER);
            sendRegistrationEmail(client);
        }
    }

    private void sendRegistrationEmail(Client client) {
    }
}
