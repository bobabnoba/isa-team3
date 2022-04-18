package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.UserRole;

public interface RoleService {

    public UserRole findById(Long id);

    public UserRole findByName(String name);

}
