package com.ftn.fishingbooker.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="Role")
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "role")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private List<User> users;

    /**
     * Do this for bidirectional relationships
     * Add Child method in Parent Entity
     * @param user another user entity that claims this role
     */
//    public void addUser(User user){
//        users.add(user);
//        user.setRole(this);
//    }
    /**
     * Do this for bidirectional relationships
     * Remove Child method in Parent Entity
     * @param user user entity no longer claims this role
     */
//    public void removeUser(User user){
//        users.remove(user);
//        user.setRole(null);
//    }


    @Override
    public String getAuthority() {
        return name;
    }
}
