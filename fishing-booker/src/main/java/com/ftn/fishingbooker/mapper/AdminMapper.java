package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.AdminDto;
import com.ftn.fishingbooker.model.Admin;

public class AdminMapper {

    public static Admin toEntity(AdminDto dto){
        Admin admin = new Admin();
        admin.setFirstLogin(true);
        admin.setActivated(true);
        admin.setFirstName(dto.getFirstName());
        admin.setLastName(dto.getLastName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        admin.setPhone(dto.getPhone());
        admin.setAddress(AddressMapper.toEntity(dto.getAddress()));
        return admin;
    }

    public static AdminDto toDto(Admin admin){
        AdminDto dto = new AdminDto();
        dto.setId(admin.getId());
        dto.setFirstName(admin.getFirstName());
        dto.setLastName(admin.getLastName());
        dto.setAddress(AddressMapper.toDto(admin.getAddress()));
        dto.setEmail(admin.getEmail());
        dto.setPhone(admin.getPhone());
        dto.setFirstLogin(admin.isFirstLogin());
        return dto;
    }
}
