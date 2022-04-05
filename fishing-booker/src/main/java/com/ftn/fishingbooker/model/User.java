package com.ftn.fishingbooker.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "ApplicationUser")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @Id
    @SequenceGenerator(name = "mySeqGenV1", sequenceName = "myGenV1", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV1")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String address;

    private String phone;

    private String city;

    private String country;

    private boolean isActivated;

    private boolean isBlocked;

    private boolean testRebaseAgain;

    @OneToOne(targetEntity = Registration.class)
    @JoinColumn(name = "registration_id")
    private Registration registration;

    /** TODO: Svugdje staviti targetEntity
     * Nije radilo dok nisam stavila targetEntity
     */
    @ManyToOne(targetEntity = UserRole.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private UserRole role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
