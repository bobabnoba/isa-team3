package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.UserRole;
import com.ftn.fishingbooker.repository.RoleRepository;
import com.ftn.fishingbooker.service.RoleService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private static RoleRepository repository;
    @Override
    public UserRole findById(Long id) {
        return repository.getById(id);
    }

    @Override
    public UserRole findByName(String name) {
        return repository.findByName(name);
    }
}
