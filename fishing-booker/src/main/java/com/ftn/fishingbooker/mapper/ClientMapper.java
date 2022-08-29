package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.ClientDto;
import com.ftn.fishingbooker.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public static ClientDto map(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setAddress(client.getAddress());
        clientDto.setPhone(client.getPhone());
        clientDto.setBiography(client.getBiography());
        clientDto.setRank(UserRankMapper.toDto(client.getRank()));
        clientDto.setPoints(client.getPoints());
        clientDto.setPenalties(client.getNoOfPenalties());

        return clientDto;
    }
}
