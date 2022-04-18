package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {

    @Query(value = "select * from role u where u.name = ?1", nativeQuery = true)
    public UserRole findByName(String email);
}
