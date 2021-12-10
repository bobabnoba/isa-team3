package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ApplicationUserRepository extends JpaRepository<User,Integer> {


}
