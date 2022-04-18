package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.UserRole;
import com.ftn.fishingbooker.repository.RoleRepository;
import com.ftn.fishingbooker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public UserRole findById(Long id) {
        return repository.getById(id);
    }

    @Override
    public UserRole findByName(String name) {
        return repository.findByName(name);
    }
}
