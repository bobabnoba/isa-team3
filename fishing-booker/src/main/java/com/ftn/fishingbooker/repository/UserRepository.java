package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select address from User u where u.id =?userId")
    public String getAddress(Integer userId);
}
