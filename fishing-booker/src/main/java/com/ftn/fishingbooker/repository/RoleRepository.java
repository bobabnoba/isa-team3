package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {

    @Query("select u from UserRole u where u.name = ?1")
    public UserRole findByName(String email);
}
