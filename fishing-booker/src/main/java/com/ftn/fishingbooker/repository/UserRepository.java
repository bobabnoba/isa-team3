package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Primjer eksplicitnog pisanja upita JPQL
     * Dzoker znacima ?1, ?2, .. u upit se ubacuju parametri
     * metode respektivno
     *
     * @param email
     * @return
     */
    public User findByEmail(String email);

    @Query(value = "select * from users u where u.deleted = false and u.role_id = 5", nativeQuery = true)
    Collection<Instructor> getAllActiveInstructors();
}
