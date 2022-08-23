package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

}
