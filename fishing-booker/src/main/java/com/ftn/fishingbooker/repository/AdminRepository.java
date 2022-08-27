package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByEmail(String email);
}
