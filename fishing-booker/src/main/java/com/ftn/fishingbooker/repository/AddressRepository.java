package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
