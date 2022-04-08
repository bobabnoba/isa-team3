package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Primjer eksplicitnog pisanja upita JPQL
     * Dzoker znacima ?1, ?2, .. u upit se ubacuju parametri
     * metode respektivno
     *
     * @param name
     * @return
     */
    @Query(value = "select c from client c where c.first_name = ?1", nativeQuery = true)
    public User getUserByName(String name);

    //@Query(value = "select c from user c where c.email = ?1", nativeQuery = true)
   // @Query("select u from User u where u.email = ?1")
    public User findByEmail(String email);
}
